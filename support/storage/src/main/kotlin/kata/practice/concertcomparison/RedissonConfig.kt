package kata.practice.concertcomparison

import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.config.Config
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RedissonConfig(
    private val clusterProperties: ClusterConfigurationProperties
){

    @Bean
    fun redissonClient(): RedissonClient? {
        val config = Config()
        val cluster =  config.useClusterServers()
        cluster.nodeAddresses = clusterProperties.nodes.map { node ->
            REDISSON_HOST_PREFIX + node
        }
        val redisson = Redisson.create(config)
        return redisson
    }

//    @Bean
//    fun redissonClient(): RedissonClient? {
//        var redisson: RedissonClient? = null
//        val config = Config()
//        val cluster =  config.useSingleServer()
//        config.useSingleServer().address = REDISSON_HOST_PREFIX + "127.0.0.1:6379"
//
//        redisson = Redisson.create(config)
//
//        //redisson.reactive()
//        //redisson.rxJava()
//
//        return redisson
//    }

    companion object {
        private const val REDISSON_HOST_PREFIX = "redis://"
    }
}