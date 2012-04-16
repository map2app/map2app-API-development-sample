map2app API
===========

We created this sample project to help developers in interacting with our APIs.


Inizialization
--------------

* request the access to our platform [map2app](http://www.map2app.com/)
* login to our backoffice [map2app backoffice](http://cms.map2app.com/)
* check out your [map2app API credentials][1]

You will need two informations to interact with the api:
1. your "userAccount key": you will find it [there][1] as the attribute of the tag <userAccount>
2. your "apiKey": you will find it [there][1] as a node of the tag <userAccount>

Usage
-----

* Run the tests to see the API in action:

    $ mvn clean test

Contributing
------------

1. Fork it.
2. Create a branch (`git checkout -b my_api_project`)
3. Commit your changes (`git commit -am "Added Something"`)
4. Push to the branch (`git push origin my_api_project`)
5. Create an [Issue][2] with a link to your branch
6. Enjoy one of our apps and wait

[1]: http://cms.map2app.com/openid/useraccounts/me
[2]: https://github.com/map2app/map2app-API-development-sample/issues
