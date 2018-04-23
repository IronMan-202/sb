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