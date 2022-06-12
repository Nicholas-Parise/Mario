package Mario;

/***************************
 * Nicholas Parise, 
 * Sile Keenan, 
 * ICS4U, 
 * Tetris
 ****************************/


//Manages Game states
public class GameStateManager {
 
 // private var
 private static Boolean PauseCount = false;
 private static int PauseTime = 0;
 private static Boolean EndCount = false;
 private static int EndTime = 0;
 private static Boolean MenuCount = false;
 private static int MenuTime = 0;
 private static Boolean SplashCount = false;
 private static int SplashTime = 0;
 private static Boolean NextCount = false;
 private static int NextTime = 0;

 // public var
 public static String CurrentState = "Splash";

 //-----------------

 public static void Delta(int DeltaTime) {
  // adds delta time to individual times

  if (PauseCount) {
   PauseTime += DeltaTime;
  }
  if (EndCount) {
   EndTime += DeltaTime;
  }

  if (MenuCount) {
   MenuTime += DeltaTime;
  }

  if (SplashCount) {
   SplashTime += DeltaTime;
  }

  if (NextCount) {
   NextTime += DeltaTime;
  }
 }

 //-----------------
 
 //switchs game states if conditions meet

 public static void SwitchLevel() {
  // changes the level 
  if (NextCount) {
   if (NextTime > 1000) {
    NextTime = 0;
    CurrentState = "Game";
    NextCount = false;
   // Blocks.NextLevel();
   }
  }
 }

 public static void SwitchSplash() {
  // makes the game go to menu from the splash screen
  if (SplashCount) {
   if (SplashTime > 2000) {
    CurrentState = "Menu";
    SplashCount = false;
   }
  }
 }

 public static void SwitchPause() {
  // switches from paused to game
  // or Menu to Help

  if (PauseCount) {
   if (PauseTime > 200) {
    // Pause
    if (CurrentState == "Game") {
     CurrentState = "Pause";
    // Blocks.CanMove = false;
    } else if (CurrentState == "Pause") {
     CurrentState = "Game";
   //  Blocks.CanMove = true;
     // Help
    } else if (CurrentState == "Menu") {
     CurrentState = "Help";
    } else if (CurrentState == "Help") {
     CurrentState = "Menu";
    }
    PauseCount = false;
    PauseTime = 0;
   }
  }

 }

 public static void SwitchEnd() {
  // switches to game over
  if (EndCount) {
   if (EndTime > 500) {
    EndCount = false;

    CurrentState = "GameOver";
    EndTime = 0;
 //   Blocks.CanMove = false;

    // checks if new High score
   // ScoreManager.TestHS();

 //   if(ScoreManager.NewHighScore){
  //   ScoreManager.StartInput();
     // starts the input of name
 //   }else{
     // if not new highscore go to menu
     StartMenu();
  //  }
   }
  }
 }

 public static void SwitchMenu() {
  // switches to menu
  if (MenuCount) {
   if (MenuTime > 2500) {

    Menu();
   }
  }
 }
 
 // ---------

 // allows timer to start
 public static void StartPause() {
  PauseCount = true;
 }

 public static void StartEnd() {
  EndCount = true;
 }

 public static void StartMenu() {
  MenuCount = true;
 }

 public static void EndSplash() {
  SplashCount = true;
 }

 public static void StartNextLevel() {
  NextCount = true;
  CurrentState = "Next";
 }

 // ---------

 // instanly switches state

 public static void Menu() {

  MenuCount = false;
  CurrentState = "Menu";
  MenuTime = 0;
 // Blocks.CanMove = false;
 }

 public static void StartGame() {

  if (CurrentState == "Menu") {
   CurrentState = "Game";
 //  Blocks.ResetGame();
  }
 }

 public static void GameOver() {
  CurrentState = "GameOver";
 }

 public static void Help() {
  if (CurrentState == "Menu") {
   CurrentState = "Help";
  }
 }

}