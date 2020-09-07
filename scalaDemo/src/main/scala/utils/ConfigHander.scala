package utils

import java.io.File

import com.typesafe.config.ConfigFactory


object ConfigHander {
  val filePath = System.getProperty("user.dir")

  private val config = ConfigFactory.parseFile(new File(filePath+ "/application.properties"))

//  private val config = ConfigFactory.load()
  //spark
  val spark_master: String = config.getString("Spark.master")
  val spark_appname: String = config.getString("Spark.appname")
  //数据库配置及表信息
  //Oracle
  val db_driver: String = config.getString("Odb.driver")
  val db_url: String = config.getString("Odb.url")
  val db_user: String = config.getString("Odb.user")
  val db_password: String = config.getString("Odb.password")
  val db_table:String=config.getString("Odb.table")
  val db_publickey:String = config.getString("Odb.publickey")

  val db_initialPoolSize = config.getInt("Odb.initialPoolSize")
  val db_maxIdleTime = config.getInt("Odb.maxIdleTime")
  val db_maxPoolSize = config.getInt("Odb.maxPoolSize")
  val db_minPoolSize = config.getInt("Odb.minPoolSize")







}
