package br.edu.ifpb.tsi.pdist.cinema.service;

import java.util.List;

import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class CinemaService {
   private static JedisPool connection = new JedisPool("redis", 6379); 

   public CinemaService() {

   }

   public List<String> getInformacoes(String key) {
       try (Jedis jedis = connection.getResource()) {
            return jedis.lrange(key, 0, -1);   
        }

   }

   public void criar(String key, String value) {
        try (Jedis jedis = connection.getResource()) {
            jedis.lpush(key, value);
        }       
   }

   public Long getIndiceElemento(String key, String value) {
        try (Jedis jedis = connection.getResource()) {
            return jedis.lpos(key, value);
        }       
   }

   public boolean atualizarElemento(String key, String oldName, String newName) {

        Long index = getIndiceElemento(key, oldName);
        
        if (index != null) {
            try (Jedis jedis = connection.getResource()) {
                jedis.lset(key, index, newName);
                return true;
            }
        } else {
            return false;
        }
        
   }

   public long deletarElemento(String key, String value) {
        try (Jedis jedis = connection.getResource()) {
            return jedis.lrem(key, 1, value);
        }
   }

}
