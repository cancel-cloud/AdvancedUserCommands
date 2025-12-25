# Advanced User Commands

A Minecraft Fabric mod that provides advanced client-side commands for enhanced gameplay and item management.

## Features

### Copy NBT Command (`/copynbt`)
Copy item NBT data from the item in your main hand directly to your clipboard in SNBT (String NBT) format.

**Key Features:**
- **Smart Safety Confirmations**: Automatically prompts for confirmation when copying large NBT data (>5000 characters)
- **Flexible Copy Modes**:
  - `ALL`: Copy all item components
  - `ESSENTIAL`: Copy only essential components (damage, enchantments, etc.)
  - `CUSTOM`: Select specific component categories to include
- **Component Filtering**: Fine-grained control over which data components to include
- **Clipboard Integration**: Automatic clipboard management with system integration
- **Interactive Confirmations**: Click `[✅]` to confirm or `[❌]` to cancel large copies

**Usage:**
```
/copynbt           - Copy item NBT from main hand
/copynbt confirm   - Confirm pending copy operation
/copynbt cancel    - Cancel pending copy operation
```

## Requirements

- **Minecraft**: 1.21.10
- **Fabric Loader**: 0.18.2 or higher
- **Fabric API**: 0.138.4+1.21.10 or higher
- **Fabric Language Kotlin**: 1.13.6+kotlin.2.2.20 or higher
- **Cloth Config**: 20.0.0 or higher
- **Java**: 21 or higher

**Optional:**
- **ModMenu**: 16.0.0-rc.2 or higher (for in-game configuration)

## Installation

1. Download and install [Fabric Loader](https://fabricmc.net/use/)
2. Download the required dependencies:
   - [Fabric API](https://modrinth.com/mod/fabric-api)
   - [Fabric Language Kotlin](https://modrinth.com/mod/fabric-language-kotlin)
   - [Cloth Config](https://modrinth.com/mod/cloth-config)
   - [ModMenu](https://modrinth.com/mod/modmenu) (optional)
3. Place all downloaded `.jar` files in your `.minecraft/mods` folder
4. Launch Minecraft with the Fabric profile

## Configuration

The mod can be configured through ModMenu or by editing the configuration file directly.

**Config File Location:** `.minecraft/config/advanced-user-commands.json`

**Available Settings:**

### Copy Modes
- **ALL**: Copies all item components including metadata
- **ESSENTIAL**: Copies only essential components:
  - Damage
  - Enchantments
  - Custom Name
  - Lore
  - Unbreakable status
  - And other critical data
- **CUSTOM**: Choose specific component categories to include

### Component Categories
When using `CUSTOM` mode, you can select from various component categories including:
- Damage and Durability
- Enchantments
- Display (Name, Lore, Colors)
- Storage (Bundles, Shulker Boxes)
- Potions and Food
- Fireworks and Banners
- Books and Maps
- Tools and Armor
- And many more...

### Additional Options
- **Include Empty Values**: Toggle whether to include components with empty/default values

## Building from Source

### Prerequisites
- JDK 21 or higher
- Git

### Build Steps
```bash
# Clone the repository
git clone https://github.com/cancel-cloud/AdvancedUserCommands.git
cd AdvancedUserCommands

# Build the mod
./gradlew build

# The compiled .jar will be in build/libs/
```

### Development
```bash
# Run Minecraft client for testing
./gradlew runClient

# Clean build artifacts
./gradlew clean
```

## Output Format

The mod outputs item NBT in SNBT (String NBT) format, compatible with Minecraft commands:

```
/give @s minecraft:diamond_sword[damage=10,enchantments={levels:{"minecraft:sharpness":5}}]
```

This format can be directly pasted into:
- `/give` commands
- `/summon` commands
- Command blocks
- Datapacks and functions
- External NBT editors

## Roadmap

Future commands planned for this mod:
- Additional utility commands for item management
- Enhanced data inspection tools
- Inventory management commands
- And more...

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## Support

If you encounter any issues or have suggestions, please [open an issue](https://github.com/cancel-cloud/AdvancedUserCommands/issues) on GitHub.

## Credits

- Built with [Fabric](https://fabricmc.net/)
- Uses [Cloth Config](https://github.com/shedaniel/cloth-config) for configuration UI
- Integrates with [ModMenu](https://github.com/TerraformersMC/ModMenu) for mod list visibility
