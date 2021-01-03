package com.liang.config;

import feign.Logger;
import feign.Request;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    /**
     * 日志级别
     *
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * Basic 认证配置
     * 通常我们调用的接口都是有权限控制的，
     * 很多时候可能认证的值是通过参数去传递的，
     * 还有就是通过请求头去传递认证信息，
     * 比如 Basic 认证方式。在 Feign 中我们可以直接配置 Basic 认证，代码如下所示。
     * @Configuration
     * public class FeignConfiguration {
     *     @Bean
     *     public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
     *         return new BasicAuthRequestInterceptor("user", "password");
     *     }
     * }
     */

    /**
     * 或者你可以自定义属于自己的认证方式，
     * 其实就是自定义一个请求拦截器。在请求之前做认证操作，
     * 然后往请求头中设置认证之后的信息。
     * 通过实现 RequestInterceptor 接口来自定义认证方式，代码如下所示。
     * public class FeignBasicAuthRequestInterceptor implements RequestInterceptor {
     *     public FeignBasicAuthRequestInterceptor() {
     *     }
     *     @Override
     *     public void apply(RequestTemplate template) {
     *         // 业务逻辑
     *     }
     * }
     * 然后将配置改成我们自定义的就可以了，
     * 这样当 Feign 去请求接口的时候，
     * 每次请求之前都会进入 FeignBasicAuthRequestInterceptor 的 apply 方法中，
     * 在里面就可以做属于你的逻辑了，代码如下所示。
     * @Configuration
     * public class FeignConfiguration {
     *     @Bean
     *     public FeignBasicAuthRequestInterceptor basicAuthRequestInterceptor() {
     *         return new FeignBasicAuthRequestInterceptor();
     *     }
     * }
     */

    /**
     * 超时时间配置
     * 通过 Options 可以配置连接超时时间和读取超时时间（代码如下所示），
     * Options 的第一个参数是连接超时时间（ms），默认值是 10×1000；
     * 第二个是取超时时间（ms），默认值是 60×1000。
     */
    @Bean
    public Request.Options options() {
        return new Request.Options(5000, 10000);
    }

    /**
     * Feign 中提供了自定义的编码解码器设置，
     * 同时也提供了多种编码器的实现，比如 Gson、Jaxb、Jackson。
     * 我们可以用不同的编码解码器来处理数据的传输。
     * 如果你想传输 XML 格式的数据，可以自定义 XML 编码解码器来实现获取使用官方提供的 Jaxb。
     * <p>
     * 配置编码解码器只需要在 Feign 的配置类中注册 Decoder 和 Encoder 这两个类即可，代码如下所示。
     */
    @Bean
    public Decoder decoder() {
        return new Decoder.Default();
    }

    @Bean
    public Encoder encoder() {
        return new Encoder.Default();
    }
}