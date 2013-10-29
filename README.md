mammoth
=======

This project is killed in favor of [deccanplato](https://github.com/indykish/deccanplato.git). 
===

RESTful cloud identity provisioner. Provisions a cloud identity instance running OpenAM in Amazon EC2 in a multi tenant environment.

## Running the application locally

Build with:

    $ mvn clean install

Then run with:

    $ java -jar target/dependency/webapp-runner.jar target/*.war

## Running on Heroku

Clone this project locally:

    $ git clone https://github.com/metadaddy-sfdc/spring-mvc-fulfillment-base.git

Create a new app on Heroku (make sure you have the [Heroku Toolbelt](http://toolbelt.heroku.com) installed):

    $ heroku login
    $ heroku create -s cedar

Upload the app to Heroku:

    $ git push heroku master

Open the app in your browser:

    $ heroku open
