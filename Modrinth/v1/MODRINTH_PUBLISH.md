# Modrinth Publishing Rundown

## Modrinth Page Description (Markdown)

```markdown
# Advanced User Commands

**Powerful client-side commands to enhance your Minecraft experience**

Copy item NBT data with precision, customize your workflow, and streamline command creation with this essential utility mod.

---

## ✨ Features

### `/copynbt` - The Ultimate NBT Copy Tool

Copy NBT data from items in your hand directly to your clipboard in SNBT format - perfect for commands, datapacks, and command blocks!

#### 🎯 Three Flexible Copy Modes

**ALL Mode** - Copy everything
- Complete item data with all components
- Perfect for exact item duplication
- Includes all metadata and properties

**ESSENTIAL Mode** - Copy what matters
- Damage, enchantments, custom names, lore, and more
- Ideal for most use cases
- Clean, clutter-free output

**CUSTOM Mode** - You're in control
- Choose from 20+ component categories
- Select exactly what data you need
- Perfect for specific extraction tasks

#### 🛡️ Smart Safety Features

- **Automatic confirmations** for large NBT data (>5000 characters)
- **Interactive UI** with click-to-confirm buttons `[✅]` `[❌]`
- **Clipboard integration** with system-level support
- **Real-time feedback** on copy operations

#### ⚙️ Component Categories (CUSTOM Mode)

Pick and choose from comprehensive categories:
- Damage & Durability
- Enchantments & Effects
- Display (Name, Lore, Colors)
- Container Contents (Bundles, Shulker Boxes, etc.)
- Potions & Food Properties
- Fireworks & Explosions
- Books & Written Content
- Maps & Decorations
- Tool & Armor Properties
- And many more...

---

## 🎮 Usage

### Basic Usage
```
1. Hold an item in your main hand
2. Run /copynbt
3. Paste the NBT data wherever you need it!
```

### Example: Copying an Enchanted Sword
```
/copynbt
→ Copies: minecraft:diamond_sword[damage=10,enchantments={levels:{"minecraft:sharpness":5}}]

/give @s minecraft:diamond_sword[damage=10,enchantments={levels:{"minecraft:sharpness":5}}]
→ Gives you an identical item!
```

### Subcommands
- `/copynbt` - Copy NBT from item in main hand
- `/copynbt confirm` - Confirm pending copy operation
- `/copynbt cancel` - Cancel pending copy operation

---

## 🔧 Configuration

Access settings through **ModMenu** or edit `.minecraft/config/advanced-user-commands.json`

**Available Options:**
- Choose default copy mode (ALL/ESSENTIAL/CUSTOM)
- Enable/disable specific component categories
- Toggle empty value inclusion
- Customize confirmation thresholds

---

## 📦 Requirements

**Required:**
- Minecraft 1.21.10
- Fabric Loader 0.18.2+
- [Fabric API](https://modrinth.com/mod/fabric-api) 0.138.4+
- [Fabric Language Kotlin](https://modrinth.com/mod/fabric-language-kotlin) 1.13.6+
- [Cloth Config](https://modrinth.com/mod/cloth-config) 20.0.0+
- Java 21+

**Optional:**
- [ModMenu](https://modrinth.com/mod/modmenu) 16.0.0-rc.2+ (for in-game configuration)

---

## 🎯 Perfect For

- **Command Block Creators** - Quickly grab item data for your creations
- **Datapack Developers** - Extract NBT for custom loot tables and recipes
- **Server Operators** - Copy items for configuration and testing
- **Map Makers** - Duplicate custom items for adventure maps
- **Technical Players** - Document and share exact item specifications

---

## 🚀 Why This Mod?

- **Client-Side Only** - Works on any server, no server-side installation needed
- **Lightweight** - Minimal performance impact
- **User-Friendly** - Intuitive interface with interactive confirmations
- **Highly Configurable** - Customize everything to your workflow
- **Open Source** - MIT licensed, contributions welcome!

---

## 📝 Output Format

Outputs SNBT (String NBT) format compatible with:
- `/give` commands
- `/summon` commands
- Command blocks
- Datapacks and functions
- External NBT editors

---

## 🔮 Future Plans

- Additional utility commands for item management
- Enhanced data inspection tools
- Inventory management commands
- More customization options
- Community-requested features

---

## 🐛 Issues & Support

Found a bug or have a suggestion? [Report it on GitHub](https://github.com/cancel-cloud/AdvancedUserCommands/issues)

---

## 📄 License

MIT License - Free and open source!

**Source Code:** [GitHub Repository](https://github.com/cancel-cloud/AdvancedUserCommands)
```

---

## 🏷️ Modrinth Tags

### Categories (Select all that apply)
- **Utility** ✅ (Primary category - provides utility commands)
- **Management** ✅ (Item/inventory management features)

### Features
- **Client-side** ✅ (Client-only mod)

### Environment
- **Client** ✅ (Client-side only)

### Loaders
- **Fabric** ✅

### Game Versions
- **1.21.10** ✅ (As specified in fabric.mod.json)

### Additional Tags/Keywords (if Modrinth supports custom tags)
- `nbt`
- `commands`
- `clipboard`
- `client-side`
- `utility`
- `item-management`
- `datapack`
- `command-blocks`
- `technical`
- `developer-tools`

---

## 📸 Screenshot Suggestions

### 1. **Hero Image** (Main Gallery Image)
**Composition:** In-game screenshot showing the mod in action
- Hold an enchanted diamond sword or similarly impressive item
- Show the `/copynbt` command being executed
- Display the success message with the SNBT output in chat
- **Setting:** Clean background (Plains biome, good lighting)
- **Time:** Day time for good visibility
- **UI:** F1 mode OFF (show full HUD for authenticity)

### 2. **ModMenu Configuration Screen**
**Composition:** The in-game configuration UI
- Open ModMenu → Advanced User Commands → Config
- Show the copy mode selection (ALL/ESSENTIAL/CUSTOM)
- Display the component category toggles
- **Purpose:** Demonstrate ease of configuration
- **Tip:** Make sure the UI is fully visible and readable

### 3. **Interactive Confirmation Dialog**
**Composition:** Large NBT data confirmation prompt
- Hold a filled shulker box or bundle
- Run `/copynbt` to trigger the confirmation dialog
- Show the "⚠ Large NBT (XXXX chars). Copy to clipboard? [✅] [❌]" message
- **Purpose:** Highlight the smart safety features
- **Tip:** Use hover effect on one of the buttons if possible

### 4. **Custom Mode Component Selection**
**Composition:** Configuration screen showing CUSTOM mode options
- Set copy mode to CUSTOM
- Show the extensive list of component categories
- Toggle some categories on and off to show flexibility
- **Purpose:** Demonstrate fine-grained control
- **Setting:** In ModMenu config screen

### 5. **Real-World Use Case** (Optional but recommended)
**Composition:** Command block setup using copied NBT
- Show a command block with the pasted NBT data
- Display the `/give` command with the copied SNBT
- Maybe show the resulting item in an item frame nearby
- **Purpose:** Show practical application
- **Setting:** Creative world with command blocks visible

### 6. **Comparison Shot** (Optional)
**Composition:** Side-by-side comparison of copy modes
- Three chat messages showing outputs from ALL, ESSENTIAL, and CUSTOM modes
- Same item (e.g., enchanted bow) for consistency
- **Purpose:** Illustrate the differences between modes
- **Tip:** Use screenshot editing to combine three separate screenshots

### 7. **Complex Item Example** (Optional)
**Composition:** Copying a highly customized item
- Written book with lots of pages
- Filled shulker box with various items
- Banner with complex patterns
- **Purpose:** Show the mod handles complex NBT data
- **Setting:** Good lighting, clear chat visibility

---

## 📋 Publishing Checklist

### Before Upload
- [ ] Build final release JAR: `./gradlew build`
- [ ] Test mod in clean Minecraft instance
- [ ] Verify all dependencies are correctly listed
- [ ] Take all screenshots (7 recommended, minimum 3-4)
- [ ] Prepare mod icon/logo (at least 512x512px, square)
- [ ] Review and finalize version number (v1.0.0)

### Modrinth Project Settings
- [ ] Project name: **Advanced User Commands**
- [ ] Project slug: `advanced-user-commands` or `advancedusercommands`
- [ ] Short description: "Powerful client-side commands to enhance your Minecraft experience"
- [ ] License: **MIT**
- [ ] Client-side: **Required**
- [ ] Server-side: **Unsupported**

### Version Information
- [ ] Version number: **1.0.0**
- [ ] Version title: "Initial Release" or "v1.0.0 - Initial Release"
- [ ] Game version: **1.21.10**
- [ ] Mod loader: **Fabric**
- [ ] Release channel: **Release** (not beta or alpha)

### Dependencies
Mark these as **Required**:
- [ ] Fabric API (0.138.4+1.21.10 or higher)
- [ ] Fabric Language Kotlin (1.13.6+kotlin.2.2.20 or higher)
- [ ] Cloth Config (20.0.0 or higher)

Mark as **Optional**:
- [ ] ModMenu (16.0.0-rc.2 or higher)

### External Links
- [ ] Source code: `https://github.com/cancel-cloud/AdvancedUserCommands`
- [ ] Issue tracker: `https://github.com/cancel-cloud/AdvancedUserCommands/issues`
- [ ] Wiki/Docs (optional): Link to GitHub README

### Final Steps
- [ ] Upload screenshots in order (hero image first)
- [ ] Upload mod JAR file
- [ ] Fill in changelog (use RELEASE_NOTES.md as reference)
- [ ] Double-check all tags and categories
- [ ] Preview the page before publishing
- [ ] Publish! 🚀

---

## 💡 Tips for Success

1. **Compelling Screenshots**: Use shaders or good lighting for attractive screenshots
2. **Clear Description**: The markdown above is detailed but readable - keep it that way
3. **Keywords**: Make sure your description includes searchable terms like "NBT", "commands", "clipboard", "datapack"
4. **Update Regularly**: Plan to add more commands and keep the mod updated
5. **Engage Community**: Respond to comments and issues promptly
6. **Cross-Platform**: Consider CurseForge publication as well for maximum reach

---

## 🎉 After Publishing

1. **Announcement**: Share on r/fabricmc and Minecraft modding communities
2. **Documentation**: Keep GitHub README in sync with Modrinth description
3. **Monitoring**: Watch for bug reports and feature requests
4. **Updates**: Plan version 1.1.0 with community feedback

Good luck with your launch! 🚀