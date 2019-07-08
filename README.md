# <img src="https://imgur.com/8ccrycM.png" width=64 align="right" /> VanillaEndervators


***Requires*** *[ServerAdditionsUtil](https://www.curseforge.com/minecraft/mc-mods/serveradditionsutil) and [Fabric API](https://www.curseforge.com/minecraft/mc-mods/fabric-api)*

This mod adds server-side Endervators (elevators) to Minecraft. Simply jump or sneak to teleport between Endervators that are in line vertically!

*Note: If the client does have fabric installed, then the mod will still need to be installed*

### Crafting
![Crafting recipe](https://i.imgur.com/lO7AxdA.png)  
Other coloured Endervators can be crafted in a similar fashion, or a White Endervator may be dyed by crafting it with a dye.

## Configuration
By default Endervators have no maximum range, do 1 heart of damage every time you use one, only connect to Endervators of the same colour, and don't teleport when the player is sprinting.

This can ALL be configured! The config file is called vanillaendervators_config.json and is in the config folder which should appear alongside your mods folder. When you run the mod, if a config file doesn't already exist, it will generate the default one which can then be edited to suit your needs. Here is the default config:
```json
{
  "teleportDamage": 2,
  "maxRange": 256,
  "sameColourOnly": true,
  "teleportWhileSprinting": false
}
```