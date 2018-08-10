package com.hyperiongrp.prodpipeline

import java.util.{Timer, TimerTask}

import com.hyperiongrp.Stage
import com.hyperiongrp.config.AppConfig
import com.hyperiongrp.input.{FileDownloader, SPFile}
import com.hyperiongrp.logging.SQLLogger
import com.hyperiongrp.util.DateTimeUtil

import scala.annotation.tailrec
import scala.sys.ShutdownHookThread

/**
  * This is the Stage1 Scheduler class to be invoked by bin\Stage1Scheduler.bat
  **/
object Stage1Scheduler {

  var stopit = false

  def main(args: Array[String]): Unit = {

    /*val mainThread = Thread.currentThread

    Runtime.getRuntime.addShutdownHook(new Thread(){override def run={
      println("inside add Shutdown Hook handler")
      stopit = true
      mainThread.join()
    }})*/
    
    if ("start" == args(0)) {
      start(args)
    }
    else if ("stop" == args(0)) {
      stop(args)
    }
  }


  def start(args: Array[String]): Unit = {
    println("start")
    //while (!stopit) {
      try{
        setEnvForConfFile("F:/Workspace-prod/loss-extraction/src/main/resources/application.properties")
        val time = new Timer()
        val st = new Stage1BatchJob
        time.schedule(st, 0, AppConfig.stage1SchedulerInterval)
      } catch {
        case e: Exception => println(e)
      }
      println("running")
    //}
  }

  def stop(args: Array[String]): Unit = {
    println("stop")
    //sys.ShutdownHookThread(println("exiting..lalala"))
    stopit = true
  }

}

/**
  * TimerTask implementation for Stage1
  **/
class Stage1BatchJob extends TimerTask {

  override def run(): Unit = {

    try {
      Stage1BatchJob.batchRun
    } catch {
      case ex: Exception => ex.printStackTrace()
    }
  }

}

/**
  * This is the Stage1 Batch Job class to be invoked by bin\Stage1BatchJob.bat
  **/
object Stage1BatchJob extends StandardOutputLogger(new FileLogger(getLogFilePath(Stage.Stage1))) {

  def main(args: Array[String]): Unit = {

    batchRun

  }

  def batchRun = {
    val stage1SchedulerId = DateTimeUtil.getCurrentTimeStampId

    println(s"${DateTimeUtil.getCurrentTimeStamp} ::Stage1Scheduler Starts for Schedule ID :: ${stage1SchedulerId}")

    try {

      SQLLogger.queryUnfinishedBatchIdsForReconciliation.foreach {
        batch =>

          println(s"${DateTimeUtil.getCurrentTimeStamp} :: Resumimg for batch :: ${batch} for Schedule ID ${stage1SchedulerId}")

          val (batchId, spfiles): (String, List[SPFile]) = FileDownloader.getBatch(batch)

          FileDownloader.downloadFiles(batchId, spfiles)

      }

      chainNewBatch

      @tailrec
      def chainNewBatch: Unit = {

        val (batchId, spfiles): (String, List[SPFile]) = FileDownloader.getNewBatch

        if (spfiles.size > AppConfig.minBatchSize) {

          println(s"${DateTimeUtil.getCurrentTimeStamp} :: Starting new batch for batch :: ${batchId} for Schedule ID ${stage1SchedulerId}")

          FileDownloader.downloadFiles(batchId, spfiles)

          chainNewBatch
        }

      }

      println(s"${DateTimeUtil.getCurrentTimeStamp} :: Stage1Scheduler ends successfully for Schedule ID :: ${stage1SchedulerId}")


    } catch {
      case ex: Exception =>
        ex.printStackTrace()
        println(s"${DateTimeUtil.getCurrentTimeStamp} :: Stage1Scheduler ends with error for Schedule ID :: ${stage1SchedulerId}")
    }

  }

}


