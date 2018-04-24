com.pengda.config:
    SwaggerConfig为Swagger-ui.html 配置
    RequestInfo: 为请求体格式,包装要请求的参数

    WebMvcConfigured:   定义一个拦截器，当调用到controller层，就会调用spring aop
    RequestLog:         定义一个拦截器，指定调用拦截器之前，环绕切面的内容
    LogRecordAspect：   定义一个切面，直接切入的方法，当调用任何一个方法的时候，都会激活这个aop切面，环绕切面，指定 当调用方法时要运行的内容

    dev
4.23
    创建一个服务提供者，Mall，配置入口，注册到注册中心EurekaServer,
    将Application名称定义跟EurekeClient名称相同，以便Ribbon（负载均衡调用）。
    结果：
    1，一个注册中心(EurekaServer),端口8761
    2，两个服务提供者，Eurekaclient(8762)和Mall(8763),分别向注册中心注册，
        定义application名称为 service-hi，注册到注册中心为SERVICE-HI,一遍ribbon调用
    3, 一个Ribbon(ServiceRibbon),端口8764，注册到注册中心
    4，当ServiceRibbon通过RestTemplate向SERIVCE-HI调用时，
        因为Ribbon进行了负载均衡，会轮流调用SERVICE-HI的两个接口8762,8763
4.23
    配置Feign
        Feign是一个伪客户端，即他不做任何请求处理，Feign通过注解生成request，从而简化Http api 开发的目的
        1、通过@EnableFeignClients注解，开启FeignClient
        2、根据Feign规则创建一个接口，并用@FeignClient注解，并指向请求地址
        3、程序启动后，会进行包扫描，扫描@FeifnClient注解的类，并将这些信息注入的IOC容器中
        4、当接口的方法被调用的时候，通过JDK代理，来生成具体的RequestTemplate
        5、RequestTemplate再生成request
        6、Request交给Client去处理，其中Client可以是HttpUrlConnection、HttpClient也可以是Okhttp
        7、最后Client被封装到LoadBalanceClient类，这个类结合类Ribbon做到了负载均衡、
4.24
