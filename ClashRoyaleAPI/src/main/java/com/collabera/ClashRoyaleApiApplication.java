package com.collabera;

import java.util.Formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import com.collabera.comparePlayer.ComparePlayer;
import com.collabera.consume.ConsumeAPI;
import com.collabera.print.PrintArt;
import com.collabera.repo.PlayerRepo;

/**
 * @author rutpatel
 *
 */
@SpringBootApplication
public class ClashRoyaleApiApplication implements CommandLineRunner {

	@Autowired
	PlayerRepo playerRepo;

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(ClashRoyaleApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjczNSwiaWRlbiI6IjUxMTYwODcwNzg0MzQ4OTgwMyIsIm1kIjp7fSwidHMiOjE1NjI3Njk1NDQxNTF9.9WMrd4WtSd53exiaIE40pAH1_aapQLE3jU5t6lG2LQs";
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("auth", token);

		HttpEntity<String> entity = new HttpEntity<String>("headers", headers);

		PrintArt.printCustomCR();

		while (true) {
			comparePlayersByTag(restTemplate, entity);
//			compareTopPlayers(restTemplate, entity);
//			comparePopularPlayers(restTemplate, entity);

			Thread.sleep(5000);
		}
	}

	public void comparePlayersByTag(RestTemplate restTemplate, HttpEntity<String> entity) {

		ConsumeAPI api = new ConsumeAPI(restTemplate, entity);

		ComparePlayer cmp = new ComparePlayer();

		playerRepo.deleteAll();

		String playersTag[] = { "9QLQC08V", "PQJ2Y82U2", "P89GLRYY" };
		playerRepo.saveAll(cmp.comparePlayersDetails(api.getPlayersDetails(playersTag)));

		cmp.printPlayerRank();

		System.out.println("\n  <><><><><> Players Ranking <><><><><>\n");

		Formatter fmt = new Formatter();
		fmt.format("%-10s | %-10s %s %n", "Player Tag", "Player Name", "= Rankings : +/-");
		System.out.println(fmt);
	}

//	private void compareTopPlayers(RestTemplate restTemplate, HttpEntity<String> entity) {
//
//		System.out.println("\n<><><><><> Players Ranking based upon Trophies Count <><><><><>\n");
//
//		Formatter fmt = new Formatter();
//		fmt.format("%-10s | %-20s %s %n", "Player Tag", "Player Name", "= Rankings (Trophies)");
//		System.out.println(fmt);
//
//		CompareTopPlayer.compareTopPlayerDetails(ConsumeAPI.getTopPlayerDetails(restTemplate, entity));
//
//		CompareTopPlayer.printPlayerRank();
//
//	}
//
//	private void comparePopularPlayers(RestTemplate restTemplate, HttpEntity<String> entity) {
//
//		ComparePopularPlayer.comparePopularPlayerDetails(ConsumeAPI.getPopularPlayerDetails(restTemplate, entity));
//
//		ComparePopularPlayer.printPlayerRank();
//
//		System.out.println("\n<><><><><> Players Ranking based upon Popularity <><><><><>\n");
//
//		Formatter fmt = new Formatter();
//		fmt.format("%-10s | %-20s %s %n", "Player Tag", "Player Name", "= Rankings (Hits / Day)");
//		System.out.println(fmt);
//	}

}
