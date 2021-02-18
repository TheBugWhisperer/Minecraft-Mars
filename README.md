# Minecraft-Mars
Try to survive on a Minecraft Mars!

## Installation Instructions

Download the latest release, and put the .jar file in your server's plugins folder.

Now edit the bukkit.yml file in your server folder, and add the following section at the bottom:

```
worlds:
  world_mars:
    generator: Mars
```

This will tell the server to use the Mars plugin to generate the world "world_mars". Change that name if you want to use a different world.

## Set up

You will need some way to get to your mars world!  I recommend using the Magic plugin:

https://www.spigotmc.org/resources/magic.1056/

Then you can do `/cast phase target_world world_mars` to get there! You can also do `/mconfig example fetch mars` to get all the tools you need to make this a realistic mars experience.

`/mauto add mars` to create a mars environment
`/mgive airpump` to give an air pump that can fill up an enclosed space with breathable air
`/mgive spacehelmet` to give a helmet that keeps you alive

## Building domes!

Do `/mconfig example add engineering` to enable engineering magic. Then `/mgive wolf` to get a Wolf Staff. Use the SuperShell spell to make domes as big as you want, and fill them with air using an air pump.
