package com.gh.douglasmiguel7.transactionAuthorizer.application.config

import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean

@Configuration
class MessageSourceConfig {
  @Bean
  fun messageSource(): MessageSource {
    val messageSource = ReloadableResourceBundleMessageSource()
    messageSource.setBasename("classpath:messages")
    messageSource.setDefaultEncoding("UTF-8")
    return messageSource
  }

  @Bean
  fun validator(): LocalValidatorFactoryBean {
    val bean = LocalValidatorFactoryBean()
    bean.setValidationMessageSource(messageSource())
    return bean
  }
}