# Memory Matching Game

A 2D tile-matching memory game application built in Android Studio featuring interactive card-flipping mechanics, custom drawables, and styled layouts.

---

## 📋 Overview

This project was created as a collaborative group effort by a team of three. The app is a grid-based memory game where players flip face-down cards to discover matching pairs of animal icons. The game tracks card states, enforces game loop constraints (allowing only two cards face-up at a time), and locks matched pairs until all cards are cleared.

---

## 🎯 Objectives

* Manage grid-based button UI elements dynamically paired with graphical image assets (`drawable`).
* Design and implement custom XML styles (`styles.xml`) for consistent element rendering across the layout.
* Implement array-shuffling algorithms to randomize tile placements upon starting a new game session.
* Manage stateful game logic (tracking active turns, card matches, and flipped states) in Java/Kotlin.
* Practice team collaboration using Git/GitHub for a three-person software project.

---

## 🛠️ Tech Stack & Requirements

* **IDE:** Android Studio 3.3 or higher
* **Language:** Java / Kotlin & XML
* **JDK:** Java Development Kit (JDK) 8
* **Target Platform:** Android

---

## 📺 Tutorial Reference

This project was adapted from the following tutorial:
* [How to Easily Make a Matching Game in Android Studio](https://youtu.be/BGvjScKcW1s?si=L2FfMGQ_5W2ithrr)

---

## 📝 Key Features

1. **Tile Grid & Drawables:** Uses custom animal icons mapped to buttons using background resources and back-of-card covers.
2. **Game Logic & State Management:**
   * Shuffles card arrays (`images.shuffle()`) to generate unique layout grids on every run.
   * Limits active card flips to two per turn.
   * Compares selected tiles for equality, freezing matched pairs face-up and prompting wrong pairs to flip back over.
3. **Custom Styles:** Implements centralized XML styles to maintain consistent sizing, margins, and visual appearance across all card views.

---

## 💡 Conclusion

This task performance demonstrates event-driven game logic, array manipulation, and reusable UI styling in mobile development. By engineering a functional card-matching loop, our team gained hands-on experience in managing visual game states and coordinating tasks within a multi-developer workflow.
