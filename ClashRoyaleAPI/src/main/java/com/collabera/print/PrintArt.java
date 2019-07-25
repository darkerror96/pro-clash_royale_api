package com.collabera.print;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import org.springframework.http.ResponseEntity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * @author rutpatel
 *
 */
public class PrintArt {

	public static void printAPIRespJSON(ResponseEntity<String> resp) {
		JsonElement jsonElement1 = new JsonParser().parse(resp.getBody());
		Gson gson1 = new GsonBuilder().setPrettyPrinting().create();
		String json1 = gson1.toJson(jsonElement1);
		System.out.println(json1);
	}

	public static void printCR() {

		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);

		Graphics2D graphics2D = (Graphics2D) image.getGraphics();
		graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		graphics2D.drawString("CLASH ROYALE", 12, 24);

		for (int y = 0; y < 100; y++) {
			StringBuilder stringBuilder = new StringBuilder();

			for (int x = 0; x < 100; x++) {
				stringBuilder.append(image.getRGB(x, y) == -16777216 ? " " : "0");
			}

			if (stringBuilder.toString().trim().isEmpty()) {
				continue;
			}
			System.out.println(stringBuilder);
		}

	}

	public static void printCustomCR() {
		System.out.println(
				"\n\n*----------------------------------------------------------------------------------------------------*");
		System.out.println(
				"|        0000  00         0      000  00   00     00000      000    0    0    0    00     000000     |");
		System.out.println(
				"|      0000000 00        00     00000 00   00     00 000    00000   0    0   00    00     000000     |");
		System.out.println(
				"|     000   00 00       0000   000 00 00   00     00  00   00   00  00  00  0000   00     00         |");
		System.out.println(
				"|     00       00       0000   000    00   00     00  00  00     00 00  00  0000   00     00         |");
		System.out.println(
				"|     00       00      00  00  00000  0000000     00 000  00     00 000000 00  00  00     00         |");
		System.out.println(
				"|     00       00      00  00   00000 0000000     00000   00     00  0000  00  00  00     000000     |");
		System.out.println(
				"|     00       00     00000000    000 00   00     00 00   00     00   00  00000000 00     00         |");
		System.out.println(
				"|     00       00     00000000     00 00   00     00  00  00     00   00  00000000 00     00         |");
		System.out.println(
				"|     000   00 00     00    00 00 000 00   00     00  00   00   00    00  000   00 00     00         |");
		System.out.println(
				"|      0000000 000000 00    00 000000 00   00     00   00   00000     00  00    00 000000 000000     |");
		System.out.println(
				"|        000   000000 00    00  000   00   00     00   00    000      00  00    00 000000 000000     |");
		System.out.println(
				"*----------------------------------------------------------------------------------------------------*");

	}

}
