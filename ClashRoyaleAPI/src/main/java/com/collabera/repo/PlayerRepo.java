/**
 * 
 */
package com.collabera.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.collabera.model.Player;

/**
 * @author rutpatel
 *
 */
public interface PlayerRepo extends MongoRepository<Player, String> {

}
