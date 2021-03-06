package singleplayer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Timer;

import login.SinglePlayerReadWriteTextFile;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import character.AIEnemy;
import character.AIPlayer;
import character.Armour;
import character.Boss;
import character.Weapon;

/**
 * 
 * @author Jack Dyer 1223659
 * @author Chau Nguyen 1254579
 * 
 */
public class PlayState {

	// ------------------------------------
	// USER INIT STATS
	// ------------------------------------
	public static int x = 200;
	public static int y = 100;
	public static int userWidth = 51;
	public static int userHeight = 64;
	static boolean userIsActive = true;
	static boolean userIsAttacking = false;
	public static String coins;
	public static int coinsInt;
	public static String sanDmg;
	public static int sanDmgInt;

	static AIEnemy enemy;
	static AIEnemy enemySlime;
	public static Boss boss;
	public static int bossHealth = 500;
	static AIPlayer player;

	public static int enemyWidth = 51;
	public static int enemyHeight = 53;
	public static int enPerWave = 5;
	public static boolean collision;
	static int totalEnemies = 0;
	public static int totalKills = 0;
	public static int waveNo = 0;
	public static int points = 0;

	// -----------------------------------
	// USER IMAGES FIELDS
	// ANIMATION HOLDERS TO DEDUCE ANIMATION SEQUENCES
	// -----------------------------------
	public static Animation p1currentAni;
	public static Animation p1previousAni; // USED TO DECIDE WHICH ANIMATION TO
											// USE IN
	// AMBIGUOUS CASE
	// -----------------------------------
	// PLAYER 1 (SELF) ANIMATIONS
	// -----------------------------------
	static boolean facingLeft = false;
	static SpriteSheet player1StandR;
	public static Animation p1StandRight;
	static SpriteSheet player1StandL;
	public static Animation p1StandLeft;
	static SpriteSheet player1Right;
	public static Animation p1Right;
	static SpriteSheet player1Left;
	public static Animation p1Left;
	static SpriteSheet player1LAttR;
	public static Animation p1LAttRight;
	static SpriteSheet player1LAttL;
	public static Animation p1LAttLeft;
	static SpriteSheet player1SAttR;
	public static Animation p1SAttRight;
	static SpriteSheet player1SAttL;
	public static Animation p1SAttLeft;

	// -----------------------------------
	// PLAYER 1 (SELF) ANIMATIONS (Mage Armour)
	// -----------------------------------
	// Mage Armour will be called 'A1' (A = Armour)

	static SpriteSheet player1A1StandR;
	public static Animation p1A1StandRight;
	static SpriteSheet player1A1StandL;
	public static Animation p1A1StandLeft;
	static SpriteSheet player1A1Right;
	public static Animation p1A1Right;
	static SpriteSheet player1A1Left;
	public static Animation p1A1Left;
	static SpriteSheet player1A1LAttR;
	public static Animation p1A1LAttRight;
	static SpriteSheet player1A1LAttL;
	public static Animation p1A1LAttLeft;
	static SpriteSheet player1A1SAttR;
	public static Animation p1A1SAttRight;
	static SpriteSheet player1A1SAttL;
	public static Animation p1A1SAttLeft;

	// Battle Mage Armour will be called 'A2'
	static SpriteSheet player1A2StandR;
	public static Animation p1A2StandRight;
	static SpriteSheet player1A2StandL;
	public static Animation p1A2StandLeft;
	static SpriteSheet player1A2Right;
	public static Animation p1A2Right;
	static SpriteSheet player1A2Left;
	public static Animation p1A2Left;
	static SpriteSheet player1A2LAttR;
	public static Animation p1A2LAttRight;
	static SpriteSheet player1A2LAttL;
	public static Animation p1A2LAttLeft;
	static SpriteSheet player1A2SAttR;
	public static Animation p1A2SAttRight;
	static SpriteSheet player1A2SAttL;
	public static Animation p1A2SAttLeft;

	// -----------------------------------
	// AI PLAYER ANIMATIONS 3 AIs
	// -----------------------------------
	static SpriteSheet playerAIStandR;
	public static Animation pAIStandRight;
	static SpriteSheet playerAIStandL;
	public static Animation pAIStandLeft;
	static SpriteSheet playerAIRight;
	public static Animation pAIRight;
	static SpriteSheet playerAILeft;
	public static Animation pAILeft;
	static SpriteSheet playerAILAttR;
	static Animation pAILAttRight;

	// -----------Weapons-----------------
	// -----------------------------------
	// WOODEN SWORD ANIMATIONS
	// -----------------------------------
	static SpriteSheet woodenswordStandR;
	public static Animation wsStandRight;
	static SpriteSheet woodenswordStandL;
	public static Animation wsStandLeft;
	static SpriteSheet woodenswordLAttR;
	public static Animation wsLAttRight;
	static SpriteSheet woodenswordLAttL;
	public static Animation wsLAttLeft;
	static SpriteSheet woodenswordSAttR;
	public static Animation wsSAttRight;
	static SpriteSheet woodenswordSAttL;
	public static Animation wsSAttLeft;

	// -----------------------------------
	// LONGSWORD ANIMATIONS
	// -----------------------------------
	static SpriteSheet longswordStandR;
	public static Animation lsStandRight;
	static SpriteSheet longswordStandL;
	public static Animation lsStandLeft;
	static SpriteSheet longswordLAttR;
	public static Animation lsLAttRight;
	static SpriteSheet longswordLAttL;
	public static Animation lsLAttLeft;
	static SpriteSheet longswordSAttR;
	public static Animation lsSAttRight;
	static SpriteSheet longswordSAttL;
	public static Animation lsSAttLeft;

	// -----------------------------------
	// SANGUINE ANIMATIONS
	// -----------------------------------
	static SpriteSheet sanguineStandR;
	public static Animation sStandRight;
	static SpriteSheet sanguineStandL;
	public static Animation sStandLeft;
	static SpriteSheet sanguineRight;
	public static Animation sRight;
	static SpriteSheet sanguineLeft;
	public static Animation sLeft;
	static SpriteSheet sanguineLAttR;
	public static Animation sLAttRight;
	static SpriteSheet sanguineLAttL;
	public static Animation sLAttLeft;
	static SpriteSheet sanguineSAttR;
	public static Animation sSAttRight;
	static SpriteSheet sanguineSAttL;
	public static Animation sSAttLeft;

	// CURRENT ANIMATION OF THE WEAPON THE USER SEES ON SCREEN
	public static Animation wpnCurrentAni;

	// -----------------------------------
	// ENEMY IMAGES FIELDS
	// -----------------------------------
	static Animation curAni = AIEnemy.curAni;
	static SpriteSheet enemyLSheet;
	public static Animation enemyLeft;
	static SpriteSheet enemyLHitSh;
	static Animation enemyLHit;
	static SpriteSheet enemyLAttack;
	static Animation enemyLAtt;
	static SpriteSheet enemyRSheet;
	public static Animation enemyRight;
	static SpriteSheet enemyRHitSh;
	static Animation enemyRHit;
	static SpriteSheet enemyRAttack;
	static Animation enemyRAtt;
	static SpriteSheet enemyDeadSheet;
	static Animation enemyDead;

	// -------------------------
	// BOSSES
	// -------------------------
	// GAZETI (MAGIC / IMMOBILE LR)
	/**
	 * SPRITESHEET USED: SOURCE AVAILABLE @
	 * http://sprites.technoized.com/images/sprite/ro/gazeti.png
	 */
	public static Animation gazCurAni;
	static SpriteSheet gazLeftS;
	public static Animation gazLeft;
	static SpriteSheet gazAttS;
	public static Animation gazAtt;
	static SpriteSheet gazDeadS;
	public static Animation gazDead;
	static SpriteSheet hyperBeam;
	public static Animation beam;
	public boolean isBossDeath;

	// GHOST (MAGIC / MOBILE)
	/**
	 * SPRITESHEET USED: SOURCE AVAILABLE @
	 * http://sprites.technoized.com/images/sprite/ro/wind_ghost.png
	 */
	static SpriteSheet ghostStandLeftS;
	public static Animation ghostStandLeft;
	static SpriteSheet ghostStandRightS;
	public static Animation ghostStandRight;
	static SpriteSheet ghostLeftS;
	public static Animation ghostLeft;
	static SpriteSheet ghostRightS;
	public static Animation ghostRight;
	static SpriteSheet ghostAttLeftS;
	public static Animation ghostAttLeft;
	static SpriteSheet ghostAttRightS;
	public static Animation ghostAttRight;
	static SpriteSheet ghostDeathS;
	public static Animation ghostDeath;
	static SpriteSheet lightningSheet;
	public static Animation lightning;

	// THANATOS (MELEE / MOBILE)
	/**
	 * SPRITESHEET USED: SOURCE AVAILABLE @
	 * http://sprites.technoized.com/images/sprite/ro/thanatos.png
	 */
	static SpriteSheet thanatosLeftS;
	public static Animation thanatosLeft;
	static SpriteSheet thanatosRightS;
	public static Animation thanatosRight;
	static SpriteSheet thanatosAttLeftS;
	public static Animation thanatosAttLeft;
	static SpriteSheet thanatosAttRightS;
	public static Animation thanatosAttRight;
	static SpriteSheet thanatosDeathS;
	public static Animation thanatosDeath;

	// -----------------------------------
	// CHARACTER ARRAYS
	// -----------------------------------
	public static LinkedList<AIPlayer> arrOfPlayer = new LinkedList<AIPlayer>();
	public static List<AIPlayer> playerToDie = new ArrayList<AIPlayer>();
	public static AIPlayer aI1;
	public static AIPlayer aI2;
	public static AIPlayer aI3;

	public static LinkedList<AIEnemy> arrOfEnemy = new LinkedList<AIEnemy>();
	public static List<AIEnemy> enemyToDie = new ArrayList<AIEnemy>();
	static LinkedList<AIEnemy> pursuingUser = new LinkedList<AIEnemy>();
	static LinkedList<AIEnemy> pursuingP1 = new LinkedList<AIEnemy>();
	static LinkedList<AIEnemy> pursuingP2 = new LinkedList<AIEnemy>();
	static LinkedList<AIEnemy> pursuingP3 = new LinkedList<AIEnemy>();

	static AIEnemy pursuer;

	// -----------------------------------
	// GAME PARAMETERS FIELDS
	// -----------------------------------
	public static int bgWidth = 1260;
	public static int bgHeight = 680;
	static int gameAreaWidth = 1060;
	public static int gameAreaHeight = 480;
	public static int gameAreaXStart = 200;
	public static int gameAreaYStart = 100;

	// -----------------------------------
	// GAME IMAGES
	// -----------------------------------
	static Image background;
	static Image foreground;
	static Image topground;
	// Health Bars
	static Image health;
	static Image health20;
	static Image health40;
	static Image health60;
	static Image health80;

	// User health
	static Image userHP10;
	static Image userHP20;
	static Image userHP30;
	static Image userHP40;
	static Image userHP50;
	static Image userHP60;
	static Image userHP70;
	static Image userHP80;
	static Image userHP90;
	static Image userHP;

	// Sidebar Images
	static Image leftBarBack;
	static SpriteSheet coinSS;
	static Animation coinSpin;
	static List<Integer> coinsDigits = new LinkedList<Integer>();
	private static LinkedList<Image> digitArray = new LinkedList<Image>();
	static int userCoins;
	static Image cpuLabel;
	static Image userLabel;
	static Image lightAttack;
	static Image strongAttack;
	static Image attackTimerGrad;
	
	// Tips Images
	static SpriteSheet tipsSpriteSheet;
	static Animation tips;

	static SpriteSheet explodeSheet;
	static Animation explode;
	boolean isExplode = false;

	static TimerTask timerTask;
	static Timer timer;

	static boolean enFacingLeft = false;

	// FIELD FOR AIPLAYER ATT DELAYS
	public static int attackDelay = 1000;
	public static int userTimer = 0;
	public static int userSTimer = 0;

	// FIELD FOR USER TO BE INVINCIBLE AFTER BEING HIT
	public static int defenceDelay = 1000;
	public static int defenceTimer = 0;

	// FIELD FOR ENEMY ATT DELAYS
	public static int enemyAttackDelay = 2000;
	public static int bossDelay = 5000;

	// FIELD FOR USER'S ARMOUR AND WEAPONS
	public static Armour armour;
	public static Weapon wpn;

	// FIELD FOR USER'S ATTACK RANGE
	public static float atkRange;
	public static float wpnWidth = 60;

	public static void init(GameContainer gc) throws SlickException,
			IOException {

		// ------------------------------
		// BACKGROUND THREAD
		// ------------------------------
		ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		service.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				// ------------------------------
				// REMOVE INACTIVE ENEMIES
				// ------------------------------
				arrOfEnemy.removeAll(enemyToDie);
				pursuingUser.removeAll(enemyToDie);
				pursuingP1.removeAll(enemyToDie);
				pursuingP2.removeAll(enemyToDie);
				pursuingP3.removeAll(enemyToDie);
				enemyToDie.clear();
				// ------------------------------
				// REMOVE INACTIVE PLAYERS
				// ------------------------------
				arrOfPlayer.removeAll(playerToDie);
				playerToDie.clear();

			}
		}, 3000, 50, TimeUnit.MILLISECONDS); // Starts after 5seconds
												// phase, repeats
		// 5 times a second

		// -----------------------------------
		// INIT IMAGES
		// -----------------------------------
		background = new Image("sprites/background.png");
		foreground = new Image("sprites/foreground.png");
		topground = new Image("sprites/topGround.png");
		health = new Image("sprites/healthBar.png");
		health20 = new Image("sprites/healthBar20.png");
		health40 = new Image("sprites/healthBar40.png");
		health60 = new Image("sprites/healthBar60.png");
		health80 = new Image("sprites/healthBar80.png");

		userHP = new Image("sprites/userHP.png");
		userHP10 = new Image("sprites/userHP10.png");
		userHP20 = new Image("sprites/userHP20.png");
		userHP30 = new Image("sprites/userHP30.png");
		userHP40 = new Image("sprites/userHP40.png");
		userHP50 = new Image("sprites/userHP50.png");
		userHP60 = new Image("sprites/userHP60.png");
		userHP70 = new Image("sprites/userHP70.png");
		userHP80 = new Image("sprites/userHP80.png");
		userHP90 = new Image("sprites/userHP90.png");

		leftBarBack = new Image("sprites/leftBarBack.png");
		coinSS = new SpriteSheet("sprites/coinSpin.png", 80, 80);
		coinSpin = new Animation(coinSS, 200);
//		coins = SinglePlayerReadWriteTextFile.getCoins("user");
//		coinsInt = Integer.parseInt(coins);
		cpuLabel = new Image("sprites/cpuLabel.png");
		userLabel = new Image("sprites/userLabel.png");
		lightAttack = new Image("sprites/lightAttack.png");
		strongAttack = new Image("sprites/strongAttack.png");
		attackTimerGrad = new Image("sprites/attackTimersGradient.png");

		tipsSpriteSheet = new SpriteSheet("sprites/tips/tips2.png", 200, 250);
		tips = new Animation(tipsSpriteSheet, 3000);

		// -----------------------------------
		// PLAYER 1 DECLARATION (P1)
		// Right facing animations
		// -----------------------------------
		player1Right = new SpriteSheet("sprites/p1/p1Right.png", 51, 53);
		p1Right = new Animation(player1Right, 200);
		player1Left = new SpriteSheet("sprites/p1/p1Left.png", 51, 53);
		p1Left = new Animation(player1Left, 200);
		player1StandR = new SpriteSheet("sprites/p1/p1StandRight.png", 51, 53);
		p1StandRight = new Animation(player1StandR, 200);
		player1StandL = new SpriteSheet("sprites/p1/p1StandLeft.png", 51, 53);
		p1StandLeft = new Animation(player1StandL, 200);
		player1LAttR = new SpriteSheet("sprites/p1/p1LAttRight.png", 40, 54);
		p1LAttRight = new Animation(player1LAttR, 200);
		player1LAttL = new SpriteSheet("sprites/p1/p1LAttLeft.png", 40, 54);
		p1LAttLeft = new Animation(player1LAttL, 200);
		player1SAttR = new SpriteSheet("sprites/p1/p1SAttRight.png", 53, 56);
		p1SAttRight = new Animation(player1SAttR, 300);
		player1SAttL = new SpriteSheet("sprites/p1/p1SAttLeft.png", 53, 56);
		p1SAttLeft = new Animation(player1SAttL, 300);

		player1A1Right = new SpriteSheet(
				"sprites/mageplayer/p1a1 WalkRight.png", 51, 60);
		p1A1Right = new Animation(player1A1Right, 200);
		player1A1Left = new SpriteSheet("sprites/mageplayer/p1a1 WalkLeft.png",
				51, 60);
		p1A1Left = new Animation(player1A1Left, 200);
		player1A1StandR = new SpriteSheet(
				"sprites/mageplayer/p1a1 StandRight.png", 51, 60);
		p1A1StandRight = new Animation(player1A1StandR, 200);
		player1A1StandL = new SpriteSheet(
				"sprites/mageplayer/p1a1 StandLeft.png", 51, 60);
		p1A1StandLeft = new Animation(player1A1StandL, 200);
		player1A1LAttR = new SpriteSheet(
				"sprites/mageplayer/p1a1 LAttRight.png", 40, 60);
		p1A1LAttRight = new Animation(player1A1LAttR, 200);
		player1A1LAttL = new SpriteSheet(
				"sprites/mageplayer/p1a1 LAttLeft.png", 40, 60);
		p1A1LAttLeft = new Animation(player1A1LAttL, 200);
		player1A1SAttR = new SpriteSheet(
				"sprites//mageplayer/p1a1 SAttRight.png", 53, 60);
		p1A1SAttRight = new Animation(player1A1SAttR, 300);
//		player1A1SAttL = new SpriteSheet(
//				"sprites/mageplayer/p1a1 SAttLeft.png", 53, 60);
//		p1A1SAttLeft = new Animation(player1A1SAttL, 300);

		player1A2Right = new SpriteSheet(
				"sprites/bmageplayer/p1a2 WalkRight.png", 51, 53);
		p1A2Right = new Animation(player1A2Right, 200);
		player1A2Left = new SpriteSheet(
				"sprites/bmageplayer/p1a2 WalkLeft.png", 51, 53);
		p1A2Left = new Animation(player1A2Left, 200);
		player1A2StandR = new SpriteSheet(
				"sprites/bmageplayer/p1a2 StandRight.png", 51, 53);
		p1A2StandRight = new Animation(player1A2StandR, 200);
		player1A2StandL = new SpriteSheet(
				"sprites/bmageplayer/p1a2 StandLeft.png", 51, 53);
		p1A2StandLeft = new Animation(player1A2StandL, 200);
		player1A2LAttR = new SpriteSheet(
				"sprites/bmageplayer/p1a2 LAttRight.png", 40, 54);
		p1A2LAttRight = new Animation(player1A2LAttR, 200);
		player1A2LAttL = new SpriteSheet(
				"sprites/bmageplayer/p1a2 LAttLeft.png", 40, 54);
		p1A2LAttLeft = new Animation(player1A2LAttL, 200);
		player1A2SAttR = new SpriteSheet(
				"sprites/bmageplayer/p1a2 SAttRight.png", 53, 56);
		p1A2SAttRight = new Animation(player1A2SAttR, 300);
		player1A2SAttL = new SpriteSheet(
				"sprites/bmageplayer/p1a2 SAttLeft.png", 53, 56);
		p1A2SAttLeft = new Animation(player1A2SAttL, 300);

		// -----------------------------------
		// PLAYER AI DECLARATION (AI PLAYERS)
		// -----------------------------------

		playerAIRight = new SpriteSheet("sprites/pAIRight.png", 51, 53);
		pAIRight = new Animation(playerAIRight, 200);
		playerAILeft = new SpriteSheet("sprites/pAILeft.png", 51, 53);
		pAILeft = new Animation(playerAILeft, 200);
		playerAIStandR = new SpriteSheet("sprites/pAIStandRight.png", 51, 53);
		pAIStandRight = new Animation(playerAIStandR, 200);
		playerAIStandL = new SpriteSheet("sprites/pAIStandLeft.png", 51, 53);
		pAIStandLeft = new Animation(playerAIStandL, 200);
		playerAILAttR = new SpriteSheet("sprites/pAILAttRight.png", 40, 54);
		pAILAttRight = new Animation(playerAILAttR, 200);

		armour = new Armour(SinglePlayerReadWriteTextFile.getArmour("user"));

		// P1 CUR ANIMATION - Set to standing right facing at spawn
		p1currentAni = armour.spStandRightArmourAni;

		// -----------------------------------
		// WEAPON DECLARATION FOR ALL WEAPONS
		// -----------------------------------
		woodenswordStandR = new SpriteSheet(
				"sprites/woodensword/WoodenSword Stand.png", 51, 53);
		wsStandRight = new Animation(woodenswordStandR, 200);
		woodenswordStandL = new SpriteSheet(
				"sprites/woodensword/WoodenSword StandLeft.png", 51, 53);
		wsStandLeft = new Animation(woodenswordStandL, 200);
		woodenswordLAttR = new SpriteSheet(
				"sprites/woodensword/WoodenSword LAttRight.png", 70, 54);
		wsLAttRight = new Animation(woodenswordLAttR, 200);
		woodenswordLAttL = new SpriteSheet(
				"sprites/woodensword/WoodenSword LAttLeft.png", 70, 54);
		wsLAttLeft = new Animation(woodenswordLAttL, 200);
		woodenswordSAttR = new SpriteSheet(
				"sprites/woodensword/WoodenSword SAttRight.png", 82, 56);
		wsSAttRight = new Animation(woodenswordSAttR, 300);
		woodenswordSAttL = new SpriteSheet(
				"sprites/woodensword/WoodenSword SAttLeft.png", 82, 56);
		wsSAttLeft = new Animation(woodenswordSAttL, 300);

		longswordStandR = new SpriteSheet(
				"sprites/longsword/Longsword Stand.png", 51, 53);
		lsStandRight = new Animation(longswordStandR, 200);
		longswordStandL = new SpriteSheet(
				"sprites/longsword/Longsword StandLeft.png", 51, 53);
		lsStandLeft = new Animation(longswordStandL, 200);
		longswordLAttR = new SpriteSheet(
				"sprites/longsword/Longsword LAttRight.png", 70, 54);
		lsLAttRight = new Animation(longswordLAttR, 200);
		longswordLAttL = new SpriteSheet(
				"sprites/longsword/Longsword LAttLeft.png", 70, 54);
		lsLAttLeft = new Animation(longswordLAttL, 200);
		longswordSAttR = new SpriteSheet(
				"sprites/longsword/Longsword SAttRight.png", 82, 56);
		lsSAttRight = new Animation(longswordSAttR, 300);
		longswordSAttL = new SpriteSheet(
				"sprites/longsword/Longsword SAttLeft.png", 82, 56);
		lsSAttLeft = new Animation(longswordSAttL, 300);

		sanguineStandR = new SpriteSheet("sprites/sanguine/Sanguine Stand.png",
				51, 53);
		sStandRight = new Animation(sanguineStandR, 200);
		sanguineStandL = new SpriteSheet(
				"sprites/sanguine/Sanguine StandLeft.png", 51, 53);
		sStandLeft = new Animation(sanguineStandL, 200);
		sanguineRight = new SpriteSheet(
				"sprites/sanguine/Sanguine WalkRight.png", 51, 53);
		sRight = new Animation(sanguineRight, 200);
		sanguineLeft = new SpriteSheet(
				"sprites/sanguine/Sanguine WalkLeft.png", 51, 53);
		sLeft = new Animation(sanguineLeft, 200);
		sanguineLAttR = new SpriteSheet(
				"sprites/sanguine/Sanguine LAttRight.png", 70, 54);
		sLAttRight = new Animation(sanguineLAttR, 200);
		sanguineLAttL = new SpriteSheet(
				"sprites/sanguine/Sanguine LAttLeft.png", 70, 54);
		sLAttLeft = new Animation(sanguineLAttL, 200);
		sanguineSAttR = new SpriteSheet(
				"sprites/sanguine/Sanguine SAttRight.png", 82, 56);
		sSAttRight = new Animation(sanguineSAttR, 300);
		sanguineSAttL = new SpriteSheet(
				"sprites/sanguine/Sanguine SAttLeft.png", 82, 56);
		sSAttLeft = new Animation(sanguineSAttL, 300);

		wpn = new Weapon(SinglePlayerReadWriteTextFile.getSword("user"));

		// WEAPON CUR ANIMATION - Set to standing right at spawn
		wpnCurrentAni = wpn.spStandRightWpnAni;

		// -----------------------------------

		// -----------------------------------
		// ENEMY IMAGES
		// -----------------------------------
		enemyLSheet = new SpriteSheet("sprites/enemyLeft.png", 56, 58);
		enemyLeft = new Animation(enemyLSheet, 200);
		enemyLHitSh = new SpriteSheet("sprites/enemyLHit.png", 64, 63);
		enemyLHit = new Animation(enemyLHitSh, 200);
		enemyRHitSh = new SpriteSheet("sprites/enemyRHit.png", 64, 63);
		enemyRHit = new Animation(enemyRHitSh, 200);
		enemyRSheet = new SpriteSheet("sprites/enemyRight.png", 56, 58);
		enemyRight = new Animation(enemyRSheet, 200);

		enemyDeadSheet = new SpriteSheet("sprites/enemyDead.png", 56, 72);
		enemyDead = new Animation(enemyDeadSheet, 200);

		// BOSSES ---------
		// Gazeti
		gazLeftS = new SpriteSheet("sprites/gazeti/gazetiLeft.png", 64, 106);
		gazLeft = new Animation(gazLeftS, 200);
		gazAttS = new SpriteSheet("sprites/gazeti/gazetiAttLeft.png", 69, 113);
		gazAtt = new Animation(gazAttS, 500);
		gazDeadS = new SpriteSheet("sprites/gazeti/gazetiDeath.png", 113, 135);
		gazDead = new Animation(gazDeadS, 100);
		hyperBeam = new SpriteSheet("sprites/gazeti/hyperBeam.png", 981, 14);
		beam = new Animation(hyperBeam, 500);

		// Ghost
		ghostStandLeftS = new SpriteSheet("sprites/ghost/ghostStandLeft.png",
				93, 106);
		ghostStandLeft = new Animation(ghostStandLeftS, 200);
		ghostStandRightS = new SpriteSheet("sprites/ghost/ghostStandRight.png",
				93, 106);
		ghostStandRight = new Animation(ghostStandRightS, 200);
		ghostLeftS = new SpriteSheet("sprites/ghost/ghostLeft.png", 111, 100);
		ghostLeft = new Animation(ghostLeftS, 200);
		ghostRightS = new SpriteSheet("sprites/ghost/ghostRight.png", 111, 100);
		ghostRight = new Animation(ghostRightS, 200);
		ghostAttLeftS = new SpriteSheet("sprites/ghost/ghostAttLeft.png", 85,
				134);
		ghostAttLeft = new Animation(ghostAttLeftS, 200);
		ghostAttRightS = new SpriteSheet("sprites/ghost/ghostAttRight.png", 85,
				134);
		ghostAttRight = new Animation(ghostRightS, 200);
		ghostDeathS = new SpriteSheet("sprites/ghost/ghostDeadLeft.png", 104,
				120);
		ghostDeath = new Animation(ghostDeathS, 100);

		// Thanatos
		thanatosLeftS = new SpriteSheet("sprites/thanatos/thanatosLeft.png",
				138, 248);
		thanatosLeft = new Animation(thanatosLeftS, 200);
		thanatosRightS = new SpriteSheet("sprites/thanatos/thanatosRight.png",
				138, 248);
		thanatosRight = new Animation(thanatosRightS, 200);
		thanatosAttLeftS = new SpriteSheet(
				"sprites/thanatos/thanatosAttLeft.png", 356, 292);
		thanatosAttLeft = new Animation(thanatosAttLeftS, 500);
		thanatosDeathS = new SpriteSheet("sprites/thanatos/thanatosDeath.png",
				234, 264);
		thanatosDeath = new Animation(thanatosDeathS, 400);

		// -----------------------------------
		// EFFECTS IMAGES
		// -----------------------------------
		// explodeSheet = new SpriteSheet("sprites/explodeSprite.png", 80, 96);
		// explode = new Animation(explodeSheet, 200);
		lightningSheet = new SpriteSheet("sprites/ghost/lightningStrike.png",
				61, 239);
		lightning = new Animation(lightningSheet, 200);

		// -----------------------------------

		// CREATES 3 NEW AIPlayers to help the user
		for (int a = 0; a < 3; a++) {
			player = new AIPlayer(armour.getUserHP());
			player.spawn();
			arrOfPlayer.add(player);
		}
		// Pass AIPlayers to variables
		aI1 = arrOfPlayer.get(0);
		aI2 = arrOfPlayer.get(1);
		aI3 = arrOfPlayer.get(2);
		aI1.userName = "P2";
		aI2.userName = "P3";
		aI3.userName = "P4";

	}

	public static void update(GameContainer gc, int i) throws SlickException,
			IOException {

		// ----------------
		// USER INPUTS
		// ----------------
		if (facingLeft == true) {
			p1currentAni = armour.spStandLeftArmourAni; // Defaults to standing
														// right if no
														// input
			wpnCurrentAni = wpn.spStandLeftWpnAni;
		} else { // is processed
			p1currentAni = armour.spStandRightArmourAni;
			wpnCurrentAni = wpn.spStandRightWpnAni;
		}
		// ----------------
		// AI DELAYED ATTACKS
		// ----------------
		if (aI1.isActive) {
			if (AIPlayer.time > 0) {
				// If the timer has not counted down, wait
				AIPlayer.time -= i;
			} else {
				// If AI1 has pursuing AIEnemy, check for collision
				if (!pursuingP1.isEmpty()
						&& checkCollision(aI1, pursuingP1.getFirst().xVal,
								pursuingP1.getFirst().yVal)) {
					// ATTACK FIRST ENEMY P1
					AIPlayer.curAni = armour.spLightAttRArmourAni;
					wpnCurrentAni = wpn.spLightAttRWpnAni;
					aI1.attack(pursuingP1.getFirst());
					// RESET ATTACK TIMER
					AIPlayer.time = attackDelay;
				} else if (pursuingP1.isEmpty()
						&& !pursuingUser.isEmpty()
						&& checkCollision(aI1, pursuingUser.getFirst().xVal,
								pursuingUser.getFirst().yVal)) {
					aI1.attack(pursuingUser.getFirst()); // Attack user pursuing
															// enemies once free
															// to fight
				}
			}
		}
		if (aI2.isActive) {
			if (AIPlayer.time > 0) {
				AIPlayer.time -= i;
			} else {
				// If AI2 has pursuing AIEnemy, check for collision
				if (!pursuingP2.isEmpty()
						&& checkCollision(aI2, pursuingP2.getFirst().xVal,
								pursuingP2.getFirst().yVal)) {
					// ATTACK FIRST ENEMY P2
					aI2.attack(pursuingP2.getFirst());
					AIPlayer.curAni = armour.spLightAttRArmourAni;
					wpnCurrentAni = wpn.spLightAttRWpnAni;
					// RESET ATTACK TIMER
					AIPlayer.time = attackDelay;
				} else if (pursuingP2.isEmpty()
						&& !pursuingUser.isEmpty()
						&& checkCollision(aI2, pursuingUser.getFirst().xVal,
								pursuingUser.getFirst().yVal)) {
					aI2.attack(pursuingUser.getFirst()); // Attack user pursuing
															// enemies once free
															// to fight
				}
			}

		}
		if (aI3.isActive) {
			if (AIPlayer.time > 0) {
				AIPlayer.time -= i;
			} else {
				// If AI3 has pursuing AIEnemy, check for collision
				if (!pursuingP3.isEmpty()
						&& checkCollision(aI3, pursuingP3.getFirst().xVal,
								pursuingP3.getFirst().yVal)) {
					// ATTACK FIRST ENEMY P3
					aI3.attack(pursuingP3.getFirst());
					AIPlayer.curAni = armour.spLightAttRArmourAni;
					wpnCurrentAni = wpn.spLightAttRWpnAni;
					// RESET ATTACK TIMER
					AIPlayer.time = attackDelay;
				} else if (pursuingP3.isEmpty()
						&& !pursuingUser.isEmpty()
						&& checkCollision(aI3, pursuingUser.getFirst().xVal,
								pursuingUser.getFirst().yVal)) {
					aI3.attack(pursuingUser.getFirst()); // Attack user pursuing
															// enemies once free
															// to fight
				}
			}
		}
		// -----------------------------------------------
		// ENEMY DELAYED ATTACKS
		// -----------------------------------------------
		// If the delay hasn't reached zero
		// then for each enemy, if it's pursuing target is the user and attacks
		// then delay
		if (defenceTimer > 0) {
			defenceTimer -= i;
		} else {
			for (AIEnemy en : arrOfEnemy) {
				checkEnemyAttack(en, armour, i);
			}
		}

		// -----------------------------------------------
		// BOSS DELAYED ATTACKS
		// -----------------------------------------------
		if (Boss.isBossActive) {
			if (Boss.bossTime > 0) {
				// If the timer has not counted down, wait
				Boss.bossTime -= i;

			} else {
				// ATTACK ANIMATION
				boss.attack();
				Boss.bossTime = bossDelay;

			}
		}
		// ----------------
		// PROCESS INPUTS
		// ----------------
		Input input = gc.getInput();
		// RIGHT KEYSTROKE (MOVEMENT)
		if ((input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT))
				&& x < (bgWidth - userWidth)) {
			facingLeft = false;
			x++;
			p1currentAni = armour.spWalkRightArmourAni;
			wpnCurrentAni = wpn.spWalkRightWpnAni;
		}
		// LEFT KEYSTROKE (MOVEMENT)
		if ((input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT))
				&& x > 200) {
			facingLeft = true;
			x--;
			p1currentAni = armour.spWalkLeftArmourAni;
			wpnCurrentAni = wpn.spWalkLeftWpnAni;
		}
		// DOWN KEYSTROKE (MOVEMENT)
		if ((input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_DOWN))
				&& y < (bgHeight - 100) - userHeight) {
			y++;
			if (facingLeft == true) {
				p1currentAni = armour.spWalkLeftArmourAni;
				wpnCurrentAni = wpn.spWalkLeftWpnAni;
			} else {
				p1currentAni = armour.spWalkRightArmourAni;
				wpnCurrentAni = wpn.spStandRightWpnAni;
			}
		}
		// UP KEYSTROKE (MOVEMENT)
		if ((input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP))
				&& y > 100) {
			y--;
			if (facingLeft == true) {
				p1currentAni = armour.spWalkLeftArmourAni;
				wpnCurrentAni = wpn.spWalkLeftWpnAni;
			} else {
				p1currentAni = armour.spWalkRightArmourAni;
				wpnCurrentAni = wpn.spStandRightWpnAni;
			}
		}
		// L OR Z (ATTACK) ON ATTACK TIMER
		if (userTimer > 0) {
			userTimer -= i;

		} else if (userSTimer > 0) {
			userSTimer -= i;
		} else if (input.isKeyPressed(Input.KEY_L)
				|| input.isKeyPressed(Input.KEY_Z)) {

			userAttack(x, y);

			// Checks if the user is facing left or right to set their attack
			// range.
			if (facingLeft) {
				atkRange = x - wpnWidth;
			} else if (!facingLeft) {
				atkRange = x + wpnWidth;
			}

			if (Boss.isBossActive) {
				// CHECK BOSS COLLISION
				if (atkRange >= Boss.xVal && x <= Boss.xVal + Boss.width
						&& y + userHeight >= Boss.yVal
						&& y <= Boss.yVal + boss.height && userTimer <= 0) {
					boss.hitPoints -= wpn.getWpnAttack();
					// CHECK THE BOSS HP
					checkBossHitPoints();
				}
			}
			for (AIEnemy en : arrOfEnemy) {
				checkLightEnemyCollision(en, wpn);
			}
			userTimer = attackDelay;

			// K OR SPACE ON STRONG ATTACK TIMER
		} else if (input.isKeyPressed(Input.KEY_K)
				|| input.isKeyPressed(Input.KEY_SPACE)) {

			userAttack(x, y);
			// Checks if the user is facing left or right to set their attack
			// range.
			if (facingLeft) {
				atkRange = x - wpnWidth;
			} else if (!facingLeft) {
				atkRange = x + wpnWidth;
			}

			if (Boss.isBossActive) {
				if (atkRange >= Boss.xVal && x <= Boss.xVal + Boss.width
						&& y + userHeight >= Boss.yVal
						&& y <= Boss.yVal + boss.height && userTimer <= 0) {
					boss.hitPoints -= wpn.getWpnSAttack();
					if (boss.hitPoints <= 0 && Boss.isBossActive) {
						Boss.isBossActive = false;
						boss.die(boss);

						totalKills++;
						points += 1000;
					}
				}
			}
			for (AIEnemy en : arrOfEnemy) {
				checkStrongEnemyCollision(en, wpn);
			}
			// STRONG ATTACK TIMER RESET
			userSTimer = attackDelay + 1000;

		}

		// --------------------------------------------------
		// ENEMY CREATION
		// --------------------------------------------------
		// NEW WAVE OF ENEMIES CREATED WHEN WAVE IS DEAD

		// BOSS SPAWN CHECK
		spawnBoss();

		if ((arrOfEnemy.size() == 0 && waveNo % 5 != 0) // Universally applies for enemies that should
				// not appear in waves in multiple of 5
				|| waveNo == 0 || (arrOfEnemy.size() == 0 && waveNo > 15)) { // Except for cases where wave number
																// is equal to 0 or greater than 15 
			// CREATES NEW ENEMY
			waveNo++;
			for (int z = 0; z < enPerWave; z++) {

				enemy = new AIEnemy(100);
				// SPAWNS NEW ENEMY
				enemy.spawn(0);

				// Adds enemy to relevant pursuing list
				if (enemy.spPursueTarget == 0) {
					pursuingUser.add(enemy);
				} else if (enemy.spPursueTarget == 1) {
					pursuingP1.add(enemy);
				} else if (enemy.spPursueTarget == 2) {
					pursuingP2.add(enemy);
				} else if (enemy.spPursueTarget == 3) {
					pursuingP3.add(enemy);
				}
				// ADDS ENEMY TO ACTIVE ENEMY LIST
				arrOfEnemy.add(enemy);

			}
			// UPDATES STATS
			totalEnemies += enPerWave;
//			if(waveNo % 5 == 0) {
//				enPerWave = 5;
//			} else {
//				enPerWave++;
//			}
		}

		// --------------------------------------------------
		// INPUTS TO ACTIVATE DIFFERENT GAME STATES
		// --------------------------------------------------

		if (input.isKeyPressed(Input.KEY_P)) {
			SinglePlayer.isPaused = true;
		}
		
//		while (coinsInt > 0) {
//			coinsDigits.add(0, coinsInt % 10);
//			coinsInt = coinsInt / 10;
//		}
	}

	public static void render(GameContainer gc, Graphics g)
			throws SlickException, IOException {
		// ----------------------------------------------------------------
		// BASIC DISPLAYS
		// ----------------------------------------------------------------

		// ------------------------------
		// BACKGROUND
		// ------------------------------

		background.draw(gameAreaXStart, gameAreaYStart, gameAreaWidth,
				gameAreaHeight);
		// ------------------------------
		// USER AREA
		// ------------------------------
		float stringMargin = 10;
		float userBoxHeight = 40;
		float userAreaWidth = 190;
		leftBarBack.draw(0, 0);
		// ------------------------------
		// USER STATS
		// ------------------------------
		userLabel.draw(5, (userBoxHeight));

		// DISPLAY HEALTH ACCORDING TO VALUE
		if (armour.getUserHP() > 0) {
			health.draw(stringMargin + 80, userBoxHeight, 100, 40);
			userHP.draw(stringMargin + 80, userBoxHeight,
					((float) armour.getUserHP() / (float) armour.fullHP) * 100, 40);
		}
		// ---------------------------------
		// DISPLAY COINS
		// ---------------------------------
//		for (int i = 0; i < 10; i++) {
//			digitArray.add(new Image("sprites/gameOverMenu/digits/png-" + i
//					+ ".png"));
//		}
		
//		int coinsBuffer = 0;
//		for (int digits : coinsDigits) {
//			digitArray.get(digits).draw(75 + coinsBuffer, 115);
//			coinsBuffer+=30;
//			
//		}
		coinSpin.draw(stringMargin, 90);
		
		// DISPLAY POINTS AND ATTACK TIMER
		lightAttack.draw(stringMargin, 190);
		strongAttack.draw(stringMargin + 95, 190);
		attackTimerGrad.draw(stringMargin + 95, 275, 85,
				-(((float) (userSTimer) / (float) (attackDelay + 1000)) * 85));
		attackTimerGrad.draw(stringMargin, 275, 85,
				-(((float) userTimer / (float) attackDelay) * 85));

		// TIPS DISPLAY TICKER
		tips.draw(5, 420, 190, 255);
		// ------------------------------
		// AI STATS
		// ------------------------------
		for (int y = 0; y < arrOfPlayer.size(); y++) {
			cpuLabel.draw(5, ((y + 1) * userBoxHeight) + 240);
			if (arrOfPlayer.get(y).hitPoints > 0) {
				health.draw(stringMargin + 80, ((y + 1) * userBoxHeight) + 240,
						100, 40);
				userHP.draw(stringMargin + 80, ((y + 1) * userBoxHeight) + 240,
						(arrOfPlayer.get(y).hitPoints / armour.fullHP) * 100, 40);
			}
		}

		// ------------------------------
		// GAME AREA
		// ------------------------------
		// ------------------------------
		// IF ALIVE PLAYER RENDER
		if (!pursuingP1.isEmpty()) {
			aI1.pursue(pursuingP1.getFirst().xVal,
			// First
			// AIPlayer
					pursuingP1.getFirst().yVal);
		} else if (!pursuingUser.isEmpty()) {
			aI1.pursue(pursuingUser.getFirst().xVal,
					pursuingUser.getFirst().yVal);
		} else {
			aI1.pursue(x - userWidth, y - userHeight);
		}
		// ------------------------------
		if (!pursuingP2.isEmpty()) {
			aI2.pursue(pursuingP2.getFirst().xVal,
			// Second
			// AIPlayer
					pursuingP2.getFirst().yVal);
		} else if (!pursuingUser.isEmpty()) {
			aI2.pursue(pursuingUser.getFirst().xVal,
					pursuingUser.getFirst().yVal);
		} else {

			aI2.pursue(x - (userWidth + userWidth), y);
		}
		// ------------------------------
		if (!pursuingP3.isEmpty()) {
			aI3.pursue(pursuingP3.getFirst().xVal,
			// Third
			// AIPlayer
					pursuingP3.getFirst().yVal);
		} else if (!pursuingUser.isEmpty()) {
			aI3.pursue(pursuingUser.getFirst().xVal,
					pursuingUser.getFirst().yVal);
		} else {
			aI3.pursue(x - userWidth, y + userHeight);
		}
		// ------------------------------

		// DRAWS THE PLAYERS BASED ON UPDATES
		for (AIPlayer pl : arrOfPlayer) {
			if (pl.isActive) {
				g.drawAnimation(AIPlayer.curAni, pl.xVal, pl.yVal);
				g.drawAnimation(wpnCurrentAni, pl.xVal, pl.yVal);
			}
		}
		// ------------------------------
		// IF ALIVE ENEMY RENDER
		// ------------------------------
		for (AIEnemy en : arrOfEnemy) {
			if (en.isActive) {
				// FIND RANDOMLY ASSIGNED USER TO PURSUE
				if (en.spPursueTarget == 0) {
					en.pursue(x, y);
				} else if (en.spPursueTarget == 1 && aI1.isActive) {
					en.pursue(aI1.xVal, aI1.yVal);
				} else if (en.spPursueTarget == 2 && aI2.isActive) {
					en.pursue(aI2.xVal, aI2.yVal);
				} else if (en.spPursueTarget == 3 && aI3.isActive) {
					en.pursue(aI3.xVal, aI3.yVal);
				} else {
					en.spPursueTarget = 0;
					pursuingUser.add(en);
				}
				// UPDATE EACH ENEMY COORDNIATES
				g.drawAnimation(AIEnemy.spCurAni, en.xVal, en.yVal);
				// DRAW ENEMY HEALTH BARS
				if (en.hitPoints == 80) {
					g.drawImage(health80, en.xVal - 20, en.yVal - 13);
				}
				if (en.hitPoints == 60) {
					g.drawImage(health60, en.xVal - 20, en.yVal - 13);
				}
				if (en.hitPoints == 40) {
					g.drawImage(health40, en.xVal - 20, en.yVal - 13);
				}
				if (en.hitPoints == 20) {
					g.drawImage(health20, en.xVal - 20, en.yVal - 13);
				}

			}
			if (AIEnemy.isExplode) {
				g.drawAnimation(explode, en.xVal, en.yVal);

			}

		}

		// ---------------
		// BOSS RENDERING
		// ---------------
		if (Boss.isBossActive) {

			// ------------------------------
			// BOSS LEVEL HEALTH DISPLAY
			// --- In front of foreground ---
			health.draw(Boss.xVal - 10, Boss.yVal - 30, (boss.hitPoints / boss.bossFullHP) * 100, 20);
			

			if (Boss.type == "Gazeti") {
				// Moves up and down the screen shooting a beam
				boss.move("immobile");
			} else {
				// Movement around the whole screen with magic or melee attacks
				boss.move("mobile");
			}
			if (Boss.isAttacking) {
				// player.hitPoints -= 10;
				if (Boss.type == "Gazeti") {
					g.drawAnimation(beam, gameAreaXStart + 40, Boss.yVal + 70);
					beamAttack(Boss.yVal + 70);
				}
				if (Boss.type == "Ghost") {
					Boss.bossCurAni = ghostAttLeft;
					int rndStrike = AIEnemy.getRandomUser();
					if (rndStrike == 0) {
						g.drawAnimation(lightning, x, y + userHeight - 239);
						lightningAttack(x, y);
					} else if (rndStrike == 1 && aI1.isActive) {
						g.drawAnimation(lightning, aI1.xVal, aI1.yVal
								+ userHeight - 239);
						lightningAttack(aI1.xVal, aI1.yVal);
					} else if (rndStrike == 2 && aI2.isActive) {
						g.drawAnimation(lightning, aI2.xVal, aI2.yVal
								+ userHeight - 239);
						lightningAttack(aI2.xVal, aI2.yVal);
					} else if (rndStrike == 3 && aI3.isActive) {
						g.drawAnimation(lightning, aI3.xVal, aI3.yVal
								+ userHeight - 239);
						lightningAttack(aI3.xVal, aI3.yVal);
					} else {

					}
				}
				if (Boss.type == "Thanatos") {
					Boss.bossCurAni = thanatosAttLeft;
					slashAttack(Boss.xVal, Boss.yVal);
				}
			}

			g.drawAnimation(Boss.bossCurAni, Boss.xVal, Boss.yVal);

		}
		// Boss death animation
		if (Boss.isBossDeath) {
			g.drawAnimation(Boss.deathAni, Boss.xVal, Boss.yVal);
		}

		// ------------------------------
		// IF ALIVE USER RENDER
		// ------------------------------
		if (userIsActive) {
			if (userIsAttacking && (userSTimer > 0)) {
				if (facingLeft) {
					g.drawAnimation(armour.spStrongAttLArmourAni, x, y);
					g.drawAnimation(wpn.spStrongAttLWpnAni, x - 30, y);
				}
				if (!facingLeft) {
					g.drawAnimation(armour.spStrongAttRArmourAni, x, y);
					g.drawAnimation(wpn.spStrongAttRWpnAni, x, y);
				}
			} else if (userIsAttacking){
				if (facingLeft) {
					g.drawAnimation(armour.spLightAttLArmourAni, x, y);
					g.drawAnimation(wpn.spLightAttLWpnAni, x - 30, y);
				}
				if (!facingLeft) {
					g.drawAnimation(armour.spLightAttRArmourAni, x, y);
					g.drawAnimation(wpn.spLightAttRWpnAni, x, y);
				}
			} else {
				g.drawAnimation(p1currentAni, x, y);
				g.drawAnimation(wpnCurrentAni, x, y);
			}
		}
		// ------------------------------
		// FOREGROUND & TOPGROUND
		// ------------------------------
		foreground.draw(gameAreaXStart, (gameAreaYStart + gameAreaHeight) - 60);
		topground.draw(gameAreaXStart, 0, 1100, 100);
		

	}

	/**
	 * Creates a sound when user attacks.
	 */
	public static synchronized void swordSound() {
		new Thread(new Runnable() {
			public void run() {
				try {
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem
							.getAudioInputStream(new File("sounds/swoosh1.wav"));
					clip.open(inputStream);
					clip.start();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}).start();
	}

	/**
	 * The theme is played during game play. 
	 */
	public static synchronized void themeMusic() {
		new Thread(new Runnable() {
			public void run() {
				try {
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem
							.getAudioInputStream(new File("sounds/swoosh1.wav")); // change
																					// this
																					// string
																					// to
																					// desired
																					// theme
																					// music
					clip.open(inputStream);
					clip.start();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}).start();
	}

	/**
	 * Spawns the boss when the wave number is a multiple of 5.
	 * 
	 * @throws SlickException
	 */
	public static void spawnBoss() throws SlickException {
		if (waveNo % 5 == 0 && waveNo != 0 && waveNo <= 15) {
			if (boss == null) {
				waveNo++;

				boss = new Boss(bossHealth);
				boss.init(waveNo / 5);

				bossHealth += 100;
				totalEnemies++;
			}

			if (!Boss.isBossActive) {

			}
		}
	}

	/**
	 * Check if the boss' hit points reaches to zero. If it 
	 * is then the activity of the boss is now false. 
	 * 
	 */
	public static void checkBossHitPoints() {
		if (boss.hitPoints <= 0 && Boss.isBossActive) {
			Boss.isBossActive = false;
			boss.die(boss);

			totalKills++;
			points += 1000;
		}
	}

	/**
	 * Takes the coordinates of an enemy and user and checks for a collision
	 * 
	 * @param xVal
	 *            the users referenced x coordinate
	 * @param yVal
	 *            the users referenced y coordinate
	 * @param enXVal
	 *            the enemy x coordinate
	 * @param enYVal
	 *            the enemy y coordinate
	 * @return boolean of whether a collision has occurred
	 */
	public static boolean checkCollision(float xVal, float yVal, float enXVal,
			float enYVal) {

		if (xVal + userWidth >= enXVal && xVal <= enXVal + enemyWidth
				&& yVal + userHeight >= enYVal && yVal <= enYVal + enemyHeight) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Takes an AIPlayer and enemy coordinates and returns a boolean for
	 * collision
	 * 
	 * @param player
	 *            an AIPlayer referenced for their coordinates in the method
	 * @param enXVal
	 *            the enemy x coordinate
	 * @param enYVal
	 *            the enemy y coordinate
	 * @return boolean as to whether a collision has occurred
	 */
	public static boolean checkCollision(AIPlayer player, float enXVal,
			float enYVal) {
		if (player.xVal + userWidth >= enXVal
				&& player.xVal <= enXVal + enemyWidth
				&& player.yVal + userHeight >= enYVal
				&& player.yVal <= enYVal + enemyHeight) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Changes the userIsAttacking for half a second for animations
	 * 
	 * @param xVal
	 *            the x coordinate of the user attacking position
	 * @param yVal
	 *            the y coordinate of the user attacking position
	 */
	public static void userAttack(float xVal, float yVal) {

		Thread t = new Thread() {
			public void run() {

				userIsAttacking = true;

				try {
					swordSound();
					Thread.sleep(500);
				} catch (InterruptedException e) {
				}

				userIsAttacking = false;
			}

		};
		t.start();
	}

	/**
	 * Called upon the death of the user and passes values to the game over
	 * state
	 * @throws IOException 
	 */
	public static void userDeath() throws IOException {
		
		coins = SinglePlayerReadWriteTextFile.getCoins("user");
		coinsInt = Integer.parseInt(coins) + (totalKills * 5);
		
		SinglePlayerReadWriteTextFile.setCoins("user", Integer.toString(coinsInt));
		SinglePlayerReadWriteTextFile.setSanguine("user", Integer.toString(wpn.getWpnAttack()));
		
		SinglePlayer.isGameOver = true;
		GameOverState.userWaves = waveNo;
		GameOverState.userKills = totalKills;
		GameOverState.userPoints = points;
	}

	/**
	 * Checks for collision with user and AIPlayer against beam attack Deducts
	 * health accordingly
	 * 
	 * @param yBeam
	 *            the y coordinate for the animation beam to check for collision
	 * @throws IOException 
	 */
	public static void beamAttack(float yBeam) throws IOException {
		// USER BEAM COLLISION
		if (yBeam < y + userHeight && yBeam + 14 > y) {
			// REMOVES 0.25HP EVERY BEAM COLLISION
			armour.setUserHP((float) (armour.getUserHP() - 0.25));
			if(armour.getUserHP() <= 0) {
				userDeath();
			}
		}
		// AIPLAYER 1 BEAM COLLISION
		if (yBeam < (aI1.yVal + userHeight) && yBeam + 14 > aI1.yVal) {
			// REMOVES 0.25HP EVERY BEAM COLLISION
			aI1.hitPoints -= 0.25;
		}
		// AIPLAYER 2 BEAM COLLISION
		if (yBeam < (aI2.yVal + userHeight) && yBeam + 14 > aI2.yVal) {
			// REMOVES 0.25HP EVERY BEAM COLLISION
			aI2.hitPoints -= 0.25;
		}
		// AIPLAYER 3 BEAM COLLISION
		if (yBeam < (aI3.yVal + userHeight) && yBeam + 14 > aI3.yVal) {
			// REMOVES 0.25HP EVERY BEAM COLLISION
			aI3.hitPoints -= 0.25;
		}

	}
	
	/**
	 * Checks for collision with user and AIPlayer against lightning attack Deducts
	 * health accordingly
	 * 
	 * @param xLightning, yLightning
	 *            the x and y coordinate for the lightning animation to check for collision
	 * @throws IOException 
	 */
	public static void lightningAttack(float xLightning, float yLightning) throws IOException {
		// USER LIGHTNING COLLISION
		if(xLightning == x && yLightning == y) {
			// REMOVES 1HP
			armour.setUserHP(armour.getUserHP() - 1);
			if(armour.getUserHP() <= 0) {
				userDeath();
			}
		}
		// AIPLAYER 1 LIGHTNING COLLISION
		if (xLightning == aI1.xVal && yLightning ==  aI1.yVal) {
			// REMOVES 1HP EVERY LIGHTNING COLLISION
			aI1.hitPoints--;
		}
		// AIPLAYER 2 LIGHTNING COLLISION
		if (xLightning == aI2.xVal && yLightning ==  aI2.yVal) {
			// REMOVES 1HP EVERY LIGHTNING COLLISION
			aI2.hitPoints--;
		}
		// AIPLAYER 3 LIGHTNING COLLISION
		if (xLightning == aI3.xVal && yLightning ==  aI3.yVal) {
			// REMOVES 1HP EVERY LIGHTNING COLLISION
			aI3.hitPoints--;
		}
	}
	
	/**
	 * Checks for collision with user and AIPlayer against sword attack Deducts
	 * health accordingly
	 * 
	 * @param xSlash, ySlash
	 *            the x and y coordinate for the attack animation to check for collision
	 * @throws IOException 
	 */
	public static void slashAttack(float xSlash, float ySlash) throws IOException {
		if(ySlash < y + userHeight && (ySlash + 292) > y
				&& xSlash < x + userWidth && (xSlash + Boss.width) > x) {
			// REMOVE 5HP
			armour.setUserHP(armour.getUserHP() - 5); 
			if(armour.getUserHP() <= 0) {
				userDeath();
			}
		}
		// AIPLAYER 1 SLASH COLLISION
		if (ySlash < aI1.yVal + userHeight && (ySlash + 292) > aI1.yVal
				&& xSlash < aI1.xVal + userWidth && (xSlash + Boss.width) > aI1.xVal) {
			// REMOVES 5HP EVERY SLASH COLLISION
			aI1.hitPoints -= 5;
		}
		// AIPLAYER 2 SLASH COLLISION
		if (ySlash < aI2.yVal + userHeight && (ySlash + 292) > aI2.yVal
				&& xSlash < aI2.xVal + userWidth && (xSlash + Boss.width) > aI2.xVal) {
			// REMOVES 5HP EVERY SLASH COLLISION
			aI2.hitPoints -= 5;
		}
		// AIPLAYER 3 SLASH COLLISION
		if (ySlash < aI3.yVal + userHeight && (ySlash + 292) > aI3.yVal
				&& xSlash < aI3.xVal + userWidth && (xSlash + Boss.width) > aI3.xVal) {
			// REMOVES 5HP EVERY SLASH COLLISION
			aI3.hitPoints -= 5;
		}
		
	}
	
	/**
	 * Checks the death of an enemy when their hit points is less or equal to zero.
	 * If it is then they will be inactive and be added to the enemyToDie array.
	 * 
	 * @param en
	 * @param wpn
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void checkEnemyDeath(AIEnemy en, Weapon wpn) throws NumberFormatException, IOException {
		// CHECK DEATH
		if (en.hitPoints <= 0 && en.isActive == true) {
			AIEnemy.spCurAni = enemyDead;
			en.isActive = false;
			enemyToDie.add(en);
			// en.die(en);

			// UPDATE STATISTICS
			totalKills++;
			points += 100;
			if (wpn.isSanguine) {
				wpn.setWpnAttack(Integer.parseInt(SinglePlayerReadWriteTextFile.getSanguine("user")) + totalKills);
			}
		}
	}
	
	/**
	 * Checks the attack collision of the Light attack against enemies.
	 * 
	 * @param en
	 * @param wpn
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void checkLightEnemyCollision(AIEnemy en, Weapon wpn) throws NumberFormatException, IOException {
//		for (AIEnemy en : arrOfEnemy) {

			// CHECKS FOR COLLISION
			if (checkCollision(atkRange, y, en.xVal, en.yVal)
					&& userTimer <= 0) {
				if (facingLeft) {
					enFacingLeft = false;
					AIEnemy.spCurAni = enemyLHit;
				} else if (!facingLeft) {
					enFacingLeft = true;
					AIEnemy.spCurAni = enemyRHit;
				}

				collision = true;
				// DEDUCTS HEALTHPOINTS
				en.hitPoints -= wpn.getWpnAttack();

				// KNOCKBACK
				if (facingLeft) {
					en.xVal -= 45;
				} else {
					en.xVal += 45;
				}

				checkEnemyDeath(en, wpn);
				// RESET TIMER

			}

//		}
	}
	
	/**
	 * Checks the attack collision of the Strong attack against enemies.
	 * 
	 * @param en
	 * @param wpn
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void checkStrongEnemyCollision(AIEnemy en, Weapon wpn) throws NumberFormatException, IOException {
		// CHECKS FOR COLLISION
		if (checkCollision(atkRange, y, en.xVal, en.yVal)
				&& userTimer <= 0) {
			if (facingLeft) {
				enFacingLeft = false;
				AIEnemy.spCurAni = enemyLHit;
			} else if (!facingLeft) {
				enFacingLeft = true;
				AIEnemy.spCurAni = enemyRHit;
			}

			collision = true;
			// DEDUCTS HEALTHPOINTS
			en.hitPoints -= wpn.getWpnSAttack();

			// KNOCKBACK
			if (facingLeft) {
				en.xVal -= 105;
			} else {
				en.xVal += 105;
			}

			checkEnemyDeath(en, wpn);
			// RESET TIMER

		}
	}
	
	/**
	 * Checks the enemy's attacks when they are near or on the user's/AI player's character.
	 * When they are, the user or AI will lose hit points and gradually so when the enemy is still
	 * near or on them. 
	 * 
	 * @param en
	 * @param armour
	 * @param i
	 */
	public static void checkEnemyAttack(AIEnemy en, Armour armour, int i){
		// For each enemy, if their delay hasn't reached zero then delay
		if (AIEnemy.time > 0) {
			AIEnemy.time -= i;
			// Else attack their target if in collision
		} else {
			if (en.spPursueTarget == 0
					&& checkCollision(x, y, en.xVal, en.yVal)) {
				userDamage(en);
				
				// Reset enemy attack timer
				AIEnemy.time = enemyAttackDelay;
				// Reset the user's defence timer
				defenceTimer = defenceDelay;

			} else if (en.spPursueTarget == 1
					&& checkCollision(aI1, en.xVal, en.yVal)) {
				// Checks collision
				// Deduct appropriate damage
				aI1.hitPoints -= en.attackDamage;
				if (aI1.hitPoints <= 0) {
					// If dead then remove AIPlayer from the game
					aI1.isActive = false;
					playerToDie.add(aI1);
				}
				// Reset enemy attack timer
				AIEnemy.time = enemyAttackDelay;

			} else if (en.spPursueTarget == 2
					&& checkCollision(aI2, en.xVal, en.yVal)) {
				// Checks collision
				// Deduct appropriate damage
				aI2.hitPoints -= en.attackDamage;
				if (aI2.hitPoints <= 0) {
					// If dead then remove AIPlayer from the game
					aI2.isActive = false;
					playerToDie.add(aI2);
				}
				// Reset enemy attack timer
				AIEnemy.time = enemyAttackDelay;

			} else if (en.spPursueTarget == 3
					&& checkCollision(aI3, en.xVal, en.yVal)) {
				// Checks collision
				// Deduct appropriate damage
				aI3.hitPoints -= en.attackDamage;
				if (aI3.hitPoints <= 0) {
					// If dead then remove AIPlayer from the game
					aI3.isActive = false;
					playerToDie.add(aI3);
				}
				// Reset enemy attack timer
				AIEnemy.time = enemyAttackDelay;
			}

		}
	}
	
	/**
	 * This will decrement the user's health points when they are attacked by an enemy.
	 * It will also check if the user's health points is less or equal than 0 to call
	 * in the userDeath() method.
	 * 
	 * @param en
	 */
	public static void userDamage(AIEnemy en) {
		// Checks collision
		// Deduct appropriate damage
		armour.setUserHP(armour.getUserHP() - en.attackDamage);
		if (armour.getUserHP() <= 0) {
			try {
				userDeath();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
