### SpringMVC Restful CRUD

SpringMVC的Restful风格的CRUD操作

表单标签和处理静态资源

数据绑定DataBinder

数据转换Converter &amp; 数据(日期、数值)格式化Formatter &amp; 数据校验Validator JSR 303

mvc:annotation-driven

initBinder设置WebDataBinder的一些初始设置

JSR303 &amp; Hibernate Validator &amp; LocalValidatorFactoryBean

页面上显示错误 &amp; 消息国际化

处理 JSON &amp; HttpMessageConverter&lt;T&gt;

@RequestBody/@ResponseBody：可以用来处理JSON

@HttpEntity&lt;T&gt;/@ResponseEntity&lt;T&gt;：可以用来处理文件下载

SessionLocaleResolver &amp; LocaleChangeInterceptor 

文件的上传MultipartResolver

拦截器和执行顺序

异常处理
ExceptionHandlerExceptionResolve &amp; 
ResponseStatusExceptionResolver &amp; 
DefaultHandlerExceptionResolver &amp; 

在 Spring MVC 配置文件中引用业务层的 Bean

多个 Spring IOC 容器之间可以设置为父子关系，以实现良好的解耦。

Spring MVC WEB 层容器可作为 “业务层” Spring容器的子容器：即 WEB 层容器可以引用业务层容器的 Bean，而业务层容器却访问不到 WEB 层容器的 Bean
