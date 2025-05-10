# Seekho Assignment

A simple Android app that uses the [Jikan API](https://jikan.moe) to fetch and display a list of top anime series, allowing users to view details and watch trailers.

---

## Objective

Build an Android app that:

- Fetches and displays a list of popular/top-rated anime.
- Allows users to view anime details including synopsis, rating, genres, main cast, and a trailer if available.

---

## Tech Stack

- **Language**: Kotlin  
- **Architecture**: MVVM  
- **Networking**: Retrofit + Coroutines  
- **Image Loading**: Coil  
- **Dependency Injection**: Dagger Hilt  
- **Video Playback**: Webview 
- **UI**: XML Using ConstraintLayout, LinearLayout, CardView.

---

### 1. Splash Screen
-  A custom splash screen with Seekho app logo and animation.
-  Transitions smoothly to the Home screen after 4 seconds.

---

## Features Implemented

### 2. Anime List Page (Home Screen)
- Uses Jikan API to fetch top anime: `https://api.jikan.moe/v4/top/anime`
- Displays:
  -  Poster Image
  -  Title
  -  Number of Episodes
  -  Rating

### 3. Anime Detail Page
-  Opens when an anime item is clicked.
-  Uses API: `https://api.jikan.moe/v4/anime/{anime_id}`
-  Displays:
  -  Trailer (if available) or fallback to poster
  -  Title
  -  Synopsis
  -  Genre(s)
  -  Main Cast as Broadcast
  -  Number of Episodes
  -  Rating

---

## Assumptions Made

-  Instead, the app displays Broadcast Information in place of the main cast.
-  Trailer URLs are used as-is from the API; if unavailable, the anime poster is shown instead.
-  Network errors and loading states are handled minimally to keep the implementation focused.

---

## Known Limitations

-  Some anime may not have trailers; a fallback poster image is used in such cases.
-  Main cast information is not fully parsed/displayed due to API response structure complexity.
-  No local caching or offline support implemented. 
-  Trailer embedding may vary if YouTube embed URLs are missing.

---

## Deliverables

-  Complete source code in this repository.
-  README file with assumptions, features, and known limitations.

---

## API Reference

- **Top Anime List**: `https://api.jikan.moe/v4/top/anime`
- **Anime Detail**: `https://api.jikan.moe/v4/anime/{anime_id}` 

---

## Getting Started

1. Clone the repo:
   ```bash
    https://github.com/ritikraushan80/Seekho.git
