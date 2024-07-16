package com.pbp.bcnctest

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.cloud.openfeign.FeignAutoConfiguration



@SpringBootApplication
@EnableFeignClients
@ImportAutoConfiguration(FeignAutoConfiguration::class)
class BcncTestApplication

fun main(args: Array<String>) {
    SpringApplication.run(BcncTestApplication::class.java, *args)
}
