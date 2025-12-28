# Release Notes - v1.0.0

**Release Date:** December 25, 2025
**Minecraft Version:** 1.21.10
**Mod Loader:** Fabric

---

## 🎉 Initial Release

Welcome to the first official release of **Advanced User Commands**! This mod brings powerful client-side commands to enhance your Minecraft experience.

## ✨ Features

### `/copynbt` Command
The flagship feature of this release - a sophisticated NBT copying tool that puts item data at your fingertips.

#### Core Functionality
- **Instant NBT Copying**: Copy NBT data from the item in your main hand directly to your clipboard
- **SNBT Format Output**: Data is formatted as String NBT, ready for use in commands
- **Smart Confirmations**: Automatic safety prompts for large NBT data (>5000 characters)
- **Interactive UI**: Click-to-confirm interface with `[✅]` and `[❌]` buttons

#### Copy Modes
Three flexible modes to suit different needs:

**ALL Mode**
- Copies complete item data with all components
- Perfect for exact item duplication
- Includes all metadata and properties

**ESSENTIAL Mode**
- Copies only the most important components:
  - Damage/Durability
  - Enchantments
  - Custom Names
  - Lore Text
  - Unbreakable Status
  - Repair Cost
  - And other critical data
- Ideal for most common use cases
- Cleaner output with less clutter

**CUSTOM Mode**
- Fine-grained control over included components
- Choose from 20+ component categories:
  - Damage & Durability
  - Enchantments & Effects
  - Display (Name, Lore, Colors)
  - Container Contents (Bundles, Shulker Boxes)
  - Potions & Food Properties
  - Fireworks & Explosions
  - Books & Written Content
  - Maps & Decorations
  - Tool & Armor Properties
  - And many more...
- Perfect for specific data extraction needs

#### Configuration Options
- **Include Empty Values**: Toggle whether to include components with default/empty values
- **Persistent Settings**: Configuration saved between game sessions
- **Per-Category Control**: Enable/disable specific component categories in CUSTOM mode

### ModMenu Integration
- Seamless integration with ModMenu for easy access
- In-game configuration screen with intuitive UI
- No need to edit config files manually
- Real-time settings updates

### Subcommands
- `/copynbt` - Main command to copy NBT data
- `/copynbt confirm` - Confirm a pending copy operation
- `/copynbt cancel` - Cancel a pending copy operation

## 🔧 Technical Details

### Dependencies
**Required:**
- Fabric Loader 0.18.2+
- Fabric API 0.138.4+1.21.10
- Fabric Language Kotlin 1.13.6+kotlin.2.2.20
- Cloth Config 20.0.0+
- Java 21+

**Optional:**
- ModMenu 16.0.0-rc.2+ (for in-game configuration)

### Performance
- Lightweight client-side mod
- Minimal performance impact
- Efficient clipboard handling
- Optimized NBT processing

### Compatibility
- **Client-side only**: No server installation required
- Works on any Minecraft 1.21.10 server
- Compatible with most other client-side mods
- No known conflicts

## 📦 Installation

1. Install [Fabric Loader](https://fabricmc.net/use/) for Minecraft 1.21.10
2. Download and install required dependencies:
   - [Fabric API](https://modrinth.com/mod/fabric-api)
   - [Fabric Language Kotlin](https://modrinth.com/mod/fabric-language-kotlin)
   - [Cloth Config](https://modrinth.com/mod/cloth-config)
   - [ModMenu](https://modrinth.com/mod/modmenu) (optional but recommended)
3. Place `advanced-user-commands-1.0.0.jar` in your `.minecraft/mods` folder
4. Launch Minecraft with the Fabric profile

## 📝 Usage Examples

### Basic Usage
```
1. Hold an item in your main hand
2. Run /copynbt
3. Paste the NBT data wherever you need it
```

### With /give Command
```
/copynbt (while holding a diamond sword)
→ Copies: minecraft:diamond_sword[damage=10,enchantments={levels:{"minecraft:sharpness":5}}]

/give @s minecraft:diamond_sword[damage=10,enchantments={levels:{"minecraft:sharpness":5}}]
→ Gives you an identical item
```

### Large NBT Data
```
/copynbt (while holding a filled shulker box)
→ Shows: "⚠ Large NBT (8432 chars). Copy to clipboard? [✅] [❌]"
→ Click [✅] to confirm or [❌] to cancel
```

## 🎯 Use Cases

This mod is perfect for:
- **Command Block Creators**: Quickly grab item data for command blocks
- **Datapack Developers**: Extract item NBT for custom loot tables and recipes
- **Server Operators**: Copy items for configuration and testing
- **Map Makers**: Duplicate custom items for adventure maps
- **Traders**: Share exact item specifications
- **Collectors**: Document rare items with all their properties

## 🐛 Known Issues

None at this time! If you encounter any bugs, please [report them on GitHub](https://github.com/cancel-cloud/AdvancedUserCommands/issues).

## 🔮 What's Next?

This is just the beginning! Future updates will include:
- Additional utility commands for item and inventory management
- Enhanced data inspection and analysis tools
- More customization options
- Community-requested features

Stay tuned for updates!

## 🙏 Credits

- Built with [Fabric](https://fabricmc.net/)
- Configuration UI powered by [Cloth Config](https://github.com/shedaniel/cloth-config)
- ModMenu integration via [ModMenu](https://github.com/TerraformersMC/ModMenu)
- Developed with [Fabric Language Kotlin](https://github.com/FabricMC/fabric-language-kotlin)

## 📄 License

This mod is licensed under the MIT License. See [LICENSE](../../LICENSE) for details.

---

**Download:** [GitHub Releases](https://github.com/cancel-cloud/AdvancedUserCommands/releases/tag/v1.0.0)
**Issues & Suggestions:** [GitHub Issues](https://github.com/cancel-cloud/AdvancedUserCommands/issues)
**Source Code:** [GitHub Repository](https://github.com/cancel-cloud/AdvancedUserCommands)

Thank you for using Advanced User Commands! 🚀
