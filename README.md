# pro-clash_royale_api

RoyaleAPI, the unofficial public API for Clash Royale! You can use this API to access Clash Royale API endpoints, which provides real-time information about players, clans, clan wars, tournaments and many more. You can see implementations of this API in Showcase (https://docs.royaleapi.com/#/showcase) by third-party developers and on their web site: https://royaleapi.com

Wrappers (https://docs.royaleapi.com/#/wrappers) in multiple languages are also available.

If you are new to development using REST APIs, I recommend that you try out Postman, which provides many useful tools for your daily development needs.

A simple console based Spring Starter Application consuming Top Player and Popular Player API endpoints using Spring Tool Suite 4 as an IDE and storing data in MongoDB locally.

For all beginners/semi-pro, this is perfect repository to start-off with a spring starter application describing how to consume API endpoints using RestTemplate. To consume any API endpoints of Royale API, you need token for authentication. So, I have taken care of the same using HttpHeaders.

I have made Daemon thread which makes API call every 5 seconds to get real-time information and then store it into MongoDB locally. I have used Spring Data MongoDB Repository which removes most of the boiler plate code required to connect to MongoDB .

I have also added functionality which displays Players Ranking based upon number of games won, draw and lost. Basically, you can provide Players Tag and all that players rankings will be displayed and updated every 5 seconds.

For testing the API endpoints, you can use Postman (API Development Environment) to make various GET calls and start understanding the JSON format of each API Response. 

NOTE: - Sometimes Royale API might be under maintenance, so you might get 503 Service Unavailable HTTP Status Code.
