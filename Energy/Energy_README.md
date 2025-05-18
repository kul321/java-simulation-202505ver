# ⚡ Energy Resource Management Simulation

A Java-based console simulation of an energy management system between players, buildings, and zones.  
This project models a turn-based game-like structure where players transfer energy resources, subject to rules, penalties, and validations.

## 📌 Features

- Model energy allocation across zones and buildings
- Manage energy transfers between players (alliances)
- Validate energy transfer actions with constraints
- Apply penalties for rule violations
- Turn-based game system logic

## ▶️ How to Run

1. Compile all `.java` files:
   ```bash
   javac *.java
   ```

2. Run the main program:
   ```bash
   java Main
   ```

## 📁 Files Overview

| File | Description |
|------|-------------|
| `Main.java` | Entry point |
| `Energy.java`, `EnergyPool.java` | Energy resource logic |
| `Player.java`, `Alliance.java` | Player and team modeling |
| `Zone.java`, `Building.java` | Spatial division and energy sinks/sources |
| `EnergyTransferManager.java`, `EnergyTransferValidation.java` | Transfer processing and validation logic |
| `Penalty.java` | Rules and penalty application |
| `TurnManager.java` | Controls game rounds and flow |

## 🎯 Purpose

This project was created to explore:
- Turn-based resource transfer logic
- Rule enforcement and validation in simulations
- Multi-object coordination using OOP
- Abstract modeling of real-world systems (energy grid, shared resources)

---

> 👤 GitHub: [kul321](https://github.com/kul321)
