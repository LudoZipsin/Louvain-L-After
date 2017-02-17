Louvain l'After / EatNow
========================

This application was build for a journalistic project in UCL. Some graphical bug persist and have to be fix (but won't be fix soon
since the application should not be used for another reason than the project it was build for). The purpose of the project was to raise
awareness around privacy leaks and show that an application on the play store (and any store) should not be trust only because it is
in the store. 

For the purpose of the project, it was release for beta testing in the play store with retricted access (only for friends of the
participants and a class of student in journalism. The application was "quickly" spotted by google (after a week or two) but only asked to
proove that the user accepted a license agreement (which they did). Because of the nature of the application and because the deadline for
the project ended soon, we didn't pursue the proof asked. 

Application
-----------

This application consist into 2 part: the android application and the remote server to store collected informations.

### Android part

The application present a con application and a background process to collect data.

The con application is a fully functionnal app that suggest to user a place to drink, eat or both based on the location in Louvain-la-Neuve
(the shorted place is listed first) and based on time (don't show the places closed at the current time). A real application would use
a backend to store information such as the closing time and the location. However, to speed the process of application development, we choose
to store data in source file.

the user can:

* Ask for a list of place to drink and/or eat
* See a list of place, order by nearest location
* See detailed information of the places
* Ask the application to guide them to the place they selected

The backgound process assigned a unique id to each user and send data such as application list, contact list and location to the remote server. 

This background service is kept alive when we close the application, when the device is locked, will restart at each device reboot adn will
restart if the system close it to free resources. The only way to get rid of it is to uninstall the application or to manually shut down the
process (however, we didn't test if the service is restared with manual shutdown). 

### Server part


A simple python server put on a vps. The server is about 10 line of pure python and the data are soted inside a MySQL database. 

Conclusion
----------

The most interesting part is that the background process is pretty much a standalone process. So one can build a fully functionnal application,
register the service inside the manifest, adding the files of the background service and make it start in an application instance... and TADA,
you'll have a virus like application. 

Even if the project is finished. I will work later in the application (minus the background service part) in order to have a functionnal
application. But this will be in a separated branch. I will also work on the background service part in another branch in order to have a cleaner code
and to use for another journalistic project if asked (or if somenone want to use it to raise awareness around privacy leaks). 

Since the whole application is few weeks works for a complete beginner in android development (but with basic java experiences), I CAN'T
BE TAKEN RESPONSAIBLE FOR THE MISUSE OF THE CODE I PUBLISHED. Indeed, anybody with google search, a computer and a device to test would be
able to get the exact same result (or better).
