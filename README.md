# ClashOfClansTracker
[`ClashOfClansTracker`](https://github.com/NicolasJott/ClashOfClansTracker)
is a JavaFX application using an extension of the clash of clans REST API created by me to gather game related data.
## In order to use this. You must obtain a JSON Web Token from (https://developer.clashofclans.com/) and then replace API_KEY with your token in the form of a string ("").



```.java
package coc.Tokens;

public class Tokens {
    private static final String API_KEY = "";

    public static String getAPI_KEY () {
        return API_KEY;
    }
}
```

## Run the application by running Main.
It will prompt you to enter a Clash of Clans player tag.
Below are some player tags that you can use if you don't have your own.

<pre>
#QRRVVP99
#YROGPOC2G
#288V8PV9R
</pre>

Required Packages are JavaFX and org.json
