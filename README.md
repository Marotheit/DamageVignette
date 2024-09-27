# HealthVignette
![License](https://img.shields.io/github/license/Marotheit/HealthVignette)
![GitHub Latest Release](https://img.shields.io/github/v/release/Marotheit/HealthVignette)
### Displays a vignette effect that increases with intensity as player health decreases.
This project is a Fabric rewrite of [DamageVignette](https://github.com/Octol1ttle/DamageVignette) by [Octol1ttle](https://github.com/Octol1ttle). They did all the heavy lifting, I have simply updated the mod for my personal needs.
<br /><br />

## How does the mod work?
This mod will display a vignette effect that increases with intensity as player health decreases, similar to other games of the survival genre. This vignette effect functions as a warning that the player is low on health and should take cover to regain stable condition.

The vignette effect is rendered behind interface elements, such as the hotbar, so that only vision of the world is obscured.

The mod is configurable through ModMenu, and each player's experience can be customized to their liking. Settings such as the vignette color, intensity, and vision thresholds can be configured.
<br /><br />

## How to Compile

```bash
# Test gradle to ensure your environment is acceptable.
./gradlew test
```
```bash
# Build mod jar file; The destination directory for the plugin file will be `<Project Location>\build\libs\`.
./gradlew build
```

This mod was designed to be reliable and lightweight. It does not use any non-public APIs.
Please report any issues, and I will see what I can remedy.
