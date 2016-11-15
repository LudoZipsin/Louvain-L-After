package com.example.ludovic.eatnow;

import android.app.Application;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.UUID;

/**
 * Created by Ludovic Zipsin on 29/10/16.
 * Mail: ludovic.j.r.zipsin@gmail.com
 * Github: https://github.com/LudoZipsin
 */
public class EatNowApplication  extends Application {

    private static final String TAG = "EatNowApp";
    private static EatNowApplication appInstance;

    private static final String sharedPreference = "com.example.ludovic.com.EATNOW_PREFERENCE";
    private static final String identityKey = "identity";

    // db handler
    private DBHelper dbHelper;

    @Override
    public void onCreate(){
        super.onCreate();
        appInstance = this;

        SharedPreferences preferences = getApplicationContext().getSharedPreferences(this.getSharedPreferenceString(), Context.MODE_PRIVATE);
        if (!preferences.contains(identityKey)){
            preferences.edit().putBoolean(identityKey, false).apply();
            Log.v("Application on create", "The key " + identityKey + " is " + Boolean.toString(preferences.getBoolean(identityKey, true)) + " and should be false");
        } else {
            Log.v("Application on create", "The key " + identityKey + " exists and is " + Boolean.toString(preferences.getBoolean(identityKey, false)) + " where it should be true");
        }

        dbHelper = new DBHelper(this);
        if (this.dbHelper.numberOfRowsUser() == 0){
            String userID = UUID.randomUUID().toString().replaceAll("-", "");
            this.dbHelper.insertUserID(userID);
        }
        if (this.dbHelper.numberOfRowsPlaces() == 0){
            String city = "Louvain-la-Neuve";
            this.dbHelper.insertPlace("Beerbar", 50.6692785,4.612252000000012,city,"/","Grand-Place 5, 1348 Ottignies-Louvain-la-Neuve, Belgique","Le point de rendez-vous des amateurs de bières à LLN: 12 pompes, plus de 150 bières à la carte, de quoi grignoter. Tout ça dans une ambiance pop rock conviviale ",0,1,1200,1200,1200,1200,1200,1200,1500,2700,2700,2700,2700,2700,2700,2700);
            this.dbHelper.insertPlace("Becketts",50.6692785,4.612252000000012,city,"010 84 58 00","Grand-Place 53, 1348 Ottignies-Louvain-la-Neuve","Vous avez toujours aimé le charme magique irlandais? Alors, n'hésitez plus, le Beckett's est l'endroit de vos rêves! Vous y trouverez des soirées à thèmes, des cocktails à prix réduits lors de celles-ci …",0,1,1200,1200,1200,1200,1200,1200,1400,2630,2630,2630,2630,2630,2630,2630);
            this.dbHelper.insertPlace("Gin and Juice",50.6692785,4.612252000000012,city,"010 45 46 00","Grand-Place 51, 1348 Ottignies-Louvain-la-Neuve","Envie d'exotisme, pas la peine de prendre l'avion jusqu'à Cuba pour se délecter de cocktails somptueux servis par un barman hors paire, tout droit sorti du film cocktail. Le tout dans un cadre contemporain aux sonorités latines. La carte comprend plus de 70 réalisations à chaque fois des plus surprenantes. Un must!",0,1,1600,1600,1600,1600,1600,1600,-1,2600,2600,2600,2600,2600,2600,-1);
            this.dbHelper.insertPlace("Onlywood",50.6682797,4.617002599999978,city,"010 45 03 61","Pl. des Wallons 37, 1348 Louvain-La-Neuve","Bar sud-ouest américain",1,1,1000,1000,1000,1000,1000,1800,-1,2700,2700,2700,2700,2700,2700,-1);
            this.dbHelper.insertPlace("Knokke out",50.669968,4.615562599999976,city,"010 45 55 55","Place de l'Université, 1348 Ottignies-Louvain-la-Neuve","endroit cosy, où on peut venir boire un café, pianoter sur internet, manger un morceau, discuter avec des copains autour d’une bière, regarder les grands moments de sport sur des écrans géants, écouter de la bonne musique et faire la fête… jusque tard dans la nuit.",1,1,830,830,830,830,830,1000,1400,2400,2400,2400,2400,2400,2400,2400);
            this.dbHelper.insertPlace("Brasse-temps",50.6684408,4.616096900000002,city,"010 45 70 27","Place des Brabançons 4, 1348 Louvain-La-Neuve","Brasserie fort connue des étudiants notamment grâce à la fameuse rafale. Ce café à la particularité d'être en plus une micro brasserie où est brassée la Cuvée des Trolls (Dubuisson). Une salle 'caverne' existe d'ailleurs dans le café (à droite en rentrant)",1,1,1100,1100,1100,1100,1100,1800,-1,2500,2500,2500,2500,2500,2500,-1);
            this.dbHelper.insertPlace("Grand place",50.6692785,4.612252000000012,city,"010 45 12 48","Grand place 15, 1348 Louvain-la-Neuve","Taverne-Restaurant située en plein centre de Louvain. Très belle terrasse. Belle carte de restauration et de bières spéciales (dont la Grimbergen). Accueil sympathique et châleureux.",1,1,1130,1130,1130,1130,1130,1130,-1,2400,2400,2400,2400,2400,2400,-1);
            this.dbHelper.insertPlace("Chez Augustin",50.6692785,4.612252000000012,city,"010 45 55 49","Grand-Place 39, 1348 Louvain-la-Neuve","Ambiance conviviale. Service sympa et cool. On n'est pas dans un resto chic mais dans un bar type brasserie ou on peut deguster de bonnes bieres et manger des plats symples mais tres bon",1,1,1130,1130,1130,1130,1130,1130,-1,2300,2300,2300,2300,2300,2300,-1);
            this.dbHelper.insertPlace("Afterwork bar",50.668883,4.61492480000004,city,"010 45 26 86","10 Traverse d’Ésope, 1348 Louvain-la-Neuve","L'Afterwork bar est le nouvel espace lounge et micro-clubbing implanté dans le centre de Louvain-la-Neuve",0,1,1600,1600,1600,1600,1600,1600,-1,2700,2700,2700,2700,2700,2700,-1);
            this.dbHelper.insertPlace("Vintage",50.6691154,4.614102099999968,city,"010 68 14 34","Grand-Rue 41, 1348 Louvain-La-Neuve","Le vintage bar vous accueille pour fêter tous événements sportifs ou simplement prendre un verre en terrasse.",0,1,900,900,900,900,900,1500,-1,2600,2600,2600,2600,2600,2600,-1);
            this.dbHelper.insertPlace("Cambridge",50.669121 ,4.613517,city,"010 45 13 22","Grand-rue 3, 1348 louvain la neuve","Dans un cadre 'à l'ancienne', cette brasserie est plutôt destinée à la partie 'adulte' de la population: on n'y vient pas pour guindailler ou grignoter sur le pouce, mais pour y bien manger (et boire) en piochant dans une (nouvelle) carte volumineuse et alléchante aux tarifs néanmoins contenus.",1,1,900,900,900,900,900,900,1000,2500,2500,2500,2500,2500,2500,2500);
            this.dbHelper.insertPlace("Galoute",50.66908429999999,4.613259999999968,city,"010 45 13 81","Rue Rabelais 23, 1348 Ottignies-Louvain-la-Neuve","Taverne - restaurant, située dans le centre de Louvain - La - Neuve. Accueil sympa, souriant et chaleureuse. Vaste gamme de plats, à des prix démocratiques et service rapide. Nous mettons un point d'honneur à la qualité et au service de nos plats et bierès spéciales.",1,1,800,800,800,800,800,800,1200,2500,2500,2500,2500,2500,2500,2500);
            this.dbHelper.insertPlace("The blackfriars",50.66607,4.611264000000006,city,"010 45 82 35","Rue René Magritte 7, 1348 Ottignies-Louvain-la-Neuve","le blackfiars est un vrai pub irlandais où on peut prendre une vrai pinte de guiness tout en dégustant un vrai Fish And chips",0,1,1900,1900,1900,1900,1900,-1,-1,2400,2400,2400,2400,2400,-1,-1);
            this.dbHelper.insertPlace("Backstage",50.6681746,4.619673000000034,city,"010 81 28 56","Place des Sciences 2, 1348 Louvain-la-Neuve","Dans une ambiance pop/rock, vous pouvez venir écouter des concerts live le mardi et participer à des quizz musicaux le jeudi et le vendredi. Vous pourrez gouter une grande variété de bières et de cocktails. Last but not least, nous vous proposons également une carte de restauration inédite à LLN.. ",1,1,1100,1100,1100,1100,1100,-1,-1,2600,2600,2600,2600,2600,-1,-1);
            this.dbHelper.insertPlace("café des halles",50.6697244,4.615329900000006,city,"010 45 03 34","Rue des Wallons 13, 1348 Louvain-La-neuve","Avec sa terrasse en hauteur, le café des halles sait comment faire plaisir à ses clients: que ça soit autout d'une bonne bière ou d'un repas",1,1,830,830,830,830,830,1100,1500,2500,2500,2500,2500,2500,2500,2500);
            this.dbHelper.insertPlace("Alterez-vous",50.6684408,4.616096900000002,city,"010 84 40 03","Place des Brabançons 6/A, 1348 Louvain-La-Neuve","Convivialité, consommation responsable et sensibilisation sont les leitmotivs de ce café citoyen",1,1,1130,1130,1130,1130,1130,1130,-1,2400,2400,2400,2400,2400,2400,-1);
            this.dbHelper.insertPlace("Deco'rhum",50.670652,4.610820099999955,city,"010 86 15 46","Rue Paulin Ladeuze 4, 1348 Louvain-la-Neuve","Cette rhumerie appelée Déco’rhum est plongée dans une atmosphère intimiste et sert des cocktails très variés, dans lesquels, tu t’en doutes, le rhum ne fait pas défaut. La lumière tamisée plane aussi à l’étage, où les tables basses s’entourent de fêtards. Tu peux agrémenter ton shot de rhum de quelques vapeurs de chicha",0,1,1200,1200,1200,1200,1200,1200,1500,2600,2600,2600,2600,2600,2600,2600);
            this.dbHelper.insertPlace("le gaudeamus",50.6691083,4.61635339999998,city,"010 45 02 61","Rue des Wallons 64, 1348 Louvain-la-Neuve","Une belle carte de bières, un service chaleureux et une ambiance conviviale.",0,1,930,930,930,930,930,-1,-1,2800,2800,2800,2800,2800,-1,-1);
            this.dbHelper.insertPlace("le butcher",50.669287,4.614496,city,"010 45 22 87","Grand-Rue 33, 1348 Louvain-la-Neuve","La partie restaurant vous acceuillera dans un cadre contemporain et vous proposera une cuisine variée allant de la petite restauration à une cuisine bien plus raffinée telle que nos VIANDES GRILLÉES OU POELLEES EN SPÉCIALITÉ. LE VIN EST À L'HONNEUR par une selection et une diversité hors du commun",1,1,900,900,900,900,900,900,1100,2300,2300,2300,2300,2330,2330,2200);
            this.dbHelper.insertPlace("le coup de théatre",50.669968,4.615562599999976,city,"010 45 29 66","Place de l'université 21, 1348 Louvain-la-Neuve","Carte gastronomique d'une cinquantaine de plats, ainsi qu'une très belle gamme de bierès (Grimbergen et Ciney au fût).",1,1,1000,1000,1000,1000,1000,1100,1100,2400,2400,2400,2400,2400,2400,2400);
            this.dbHelper.insertPlace("CASA",50.6681233,4.618725600000062,city,"/"," place Galilée, 1348 Louvain-la-Neuve","La Grande Casa, située place Galilée, est la plus grande salle d'animation de Louvain-la-Neuve. Chaque jour, du lundi au jeudi, vous êtes sûr d'y trouver une ambiance incroyable. Possédant un système Son et Lumière de dernier cri, vous y trouverez à coup sûr votre bonheur !",0,1,2200,2200,2200,2200,-1,-1,-1,2700,2700,2700,2700,-1,-1,-1);
            this.dbHelper.insertPlace("CESEC",50.6675303,4.611300000000028,city,"0475 82 86 66","Rue du Buret 2, 1348 Ottignies-Louvain-la-Neuve","Le CESEC est le Cercle des Étudiants en Sciences Économiques, Sociales, Politiques et de la Communication à l'Université Catholique de Louvain. ",0,1,1800,1800,1800,1800,-1,-1,1800,2500,2500,2500,2500,-1,-1,2500);
            this.dbHelper.insertPlace("CI",50.66807310000001,4.6182249999999385,city,"/","Ruelle Saint-Éloi, 1348 Louvain-la-Neuve","Le Cercle Industriel, plus communément abrégé CI, est le cercle des étudiants de l’École Polytechnique de Louvain (EPL). Il regroupe ainsi l’ensemble des étudiants ingénieurs civils, ingénieurs civils architectes et étudiants en sciences informatiques. ",0,1,1200,1200,1200,1200,1200,-1,-1,2700,1800,1800,1800,1800,-1,-1);
            this.dbHelper.insertPlace("MDS",50.6682915,4.617857700000059,city,"010 39 10 19","Pl. des Wallons 70, 1348 Ottignies-Louvain-la-Neuve","La Maison Des Sciences est le cercle des étudiants en sciences dites 'exactes' de l’UCL à Louvain-la-Neuve.",0,1,1200,1200,1200,1200,1200,-1,-1,1800,1800,1800,2700,1800,-1,-1);
            this.dbHelper.insertPlace("Adèle",50.6663237,4.6088068999999905,city,"/","Rue Pierre Joseph Redouté, 1348 Ottignies-Louvain-la-Neuve","le cercle des étudiants en droit à Lln",0,1,1200,1200,1200,1200,1200,-1,-1,1800,1800,2700,1800,1800,-1,-1);
            this.dbHelper.insertPlace("Philo et lettres",50.67065950000001,4.608626100000038,city,"/","Rue des Blancs Chevaux 8A, 1348 Louvain-la-Neuve","C'est un groupe d'étudiants qui depuis 39 ans décident de concilier études et engagement dans le milieu associatif... et souvent, le résultat est très concluant puisqu'une très grande majorité d'entre eux s'en sort très bien dans leurs études ! Mais le FLTR, c'est aussi et surtout le cercle de référence pour tous les étudiants de la faculté FIAL ! ",0,1,1200,1200,1200,1200,1200,-1,-1,2700,1800,1800,1800,1800,-1,-1);
            this.dbHelper.insertPlace("Agro",50.6679885,4.618745499999932,city,"/","Place Galilée 6, 1348 Ottignies-Louvain-la-Neuve","En plus de vous fournir des bières toujours fraiches, le cercle agro vous prépare aussi des sandwichs d'une qualité hors pairs",0,1,1200,1200,1200,1200,1200,-1,-1,1800,1800,2700,1800,1800,-1,-1);
            this.dbHelper.insertPlace("Psycho",50.6712955,4.609474699999964,city,"/","rue des blancs chevaux, 1348 Louvain-la-Neuve","Au psycho aussi, on sait vous servir des bières",0,1,1200,1200,1200,1200,1200,-1,-1,2700,1800,1800,2700,1800,-1,-1);
            this.dbHelper.insertPlace("MAF",50.6714845,4.61050309999996,city,"/","Voie Cardijn, 1348 Ottignies-Louvain-la-Neuve","Des athlètes aux gros bras sauront vous désaltérer comme il faut",0,1,1200,1200,1200,1200,1200,-1,-1,1800,2700,2700,1800,1800,-1,-1);
            this.dbHelper.insertPlace("Cœur des Sens",50.66874800000001,4.615187999999989,city,"010 45 68 85","Traverse d'Esope 12, 1348 Ottignies-Louvain-la-Neuve","Cuisine du monde, ambiance feng-shui",1,0,1100,1100,1100,1100,1100,1800,1800,2345,2345,2345,2345,2345,2345,2345);
            this.dbHelper.insertPlace("Le Petit Vingtième",50.671404,4.613054000000034,city,"010 48 84 26","Rue du Labrador 26, 1348 Ottignies-Louvain-la-Neuve","Brasserie avec terrasse du Musée Hergé",1,0,-1,1030,1030,1030,1030,1000,1000,-1,1700,1700,1700,1700,1800,1800);
            this.dbHelper.insertPlace("Loungeatude",50.665423,4.617056700000035,city,"010 45 64 62","Place Polyvalente, 1348 Ottignies-Louvain-la-Neuve","Cuisine mêlant classique et innovation, ambiance lounge",1,0,-1,1100,1100,1100,1100,1900,-1,-1,2400,2400,2400,2400,2200,2200);
            this.dbHelper.insertPlace("Brasserie RN",50.6683226,4.625300000000038,city,"010 22 99 92","Avenue Georges Lemaitre 5, 1348 Ottignies-Louvain-la-Neuve","Brasserie à l'ambiance cosy dans une ferme réaménagée",1,0,1030,1030,1030,1030,1030,1030,1030,2200,2200,2200,2200,2300,2300,2200);
            this.dbHelper.insertPlace("Empreintes Nomades",50.6703505,4.615034199999968,city,"010 88 07 17","Place de l'Université 15, 1348 Ottignies-Louvain-la-Neuve","Restaurant méditerranéen",1,0,1200,1200,1200,1200,1200,1200,-1,2300,2300,2300,2300,2300,2300,-1);
            this.dbHelper.insertPlace("Butcher",50.66915789999999,4.614485699999932,city,"010 45 22 87","Grand-Rue 33, 1348 Louvain-la-Neuve","Sandwicherie, traiteur, restaurant et boucherie",1,0,900,900,900,900,900,900,1100,2300,2300,2300,2300,2300,2330,2200);
            this.dbHelper.insertPlace("Il Doge",50.66901009999999,4.612616300000013,city,"010 45 30 63","Agora 22, 1348 Ottignies-Louvain-la-Neuve","Restaurant italien",1,0,-1,1200,1200,1200,1200,1200,1200,-1,2400,2400,2400,2400,2400,2400);
            this.dbHelper.insertPlace("La Baïta",50.66901009999999,4.612616300000013,city,"010 45 11 65","Drève du Golf, 1348 Louvain-la-Neuve","Cuisine italienne et française",1,0,-1,1200,1200,1200,1200,1200,1200,-1,2200,2200,2200,2200,2200,2200);
            this.dbHelper.insertPlace("Onlywood",50.66865379999999,4.616756200000054,city,"010 45 03 61","Place des Wallons 37, 1348 Louvain-La-Neuve","Restaurant tex-mex",1,0,1000,1000,1000,1000,1000,1800,-1,2700,2700,2700,2700,2700,2700,-1);
            this.dbHelper.insertPlace("Belgicanos",50.6698344,4.613685799999985,city,"010 22 45 58","Rue Charlemagne 27, 1348 Louvain-La-Neuve","Cuisine espagnole",1,0,1000,1000,1000,1000,1000,1000,-1,2330,2330,2330,2330,2330,2330,-1);
            this.dbHelper.insertPlace("La Petite Gayole",50.6694332,4.605047300000024,city,"010 45 46 58","Place des Ondines 3, 1348 Ottignies-Louvain-la-Neuve","Café et petite restauration",1,1,-1,1100,1100,1100,1100,1100,1100,-1,2500,2500,2500,2500,2500,2500);
            this.dbHelper.insertPlace("Le Fellini",50.66920349999999,4.614067400000067,city,"010 45 56 56","Grand-Rue 17, 1348 Ottignies-Louvain-la-Neuve","Cuisine italienne et pizza au feu de bois",1,0,1200,1200,1200,1200,1200,1200,-1,2200,2200,2200,2200,2200,2200,-1);
            this.dbHelper.insertPlace("Mex & Go",50.668799,4.614520999999968,city,"010 81 02 16","Rue du Sablon 52, 1348 Ottignies-Louvain-la-Neuve","Restauration rapide mexicaine",1,0,1130,1130,1130,1130,1130,1130,1830,2200,2200,2200,2200,2200,2200,2200);
            this.dbHelper.insertPlace("La Galoute",50.669146,4.613326000000029,city,"010 45 13 81","Rue Rabelais, 1348 Ottignies-Louvain-la-Neuve","Taverne, restaurant",1,1,800,800,800,800,800,800,1200,2500,2500,2500,2500,2500,2500,2500);
            this.dbHelper.insertPlace("Woké",50.66961269999999,4.613066900000035,city,"010 24 14 85","Rue Charlemagne 20, 1348 Louvain-la-Neuve","Wok personnalisé",1,0,1130,1130,1130,1130,1130,1130,1130,2200,2200,2200,2200,2200,2200,2200);
            this.dbHelper.insertPlace("Altérez-vous",50.6683859,4.6161429999999655,city,"010 84 40 03","Place des Brabançons 6/A, 1348 Louvain-La-Neuve","Café citoyen avec concert et scène ouverte",1,0,1130,1130,1130,1130,1130,1130,-1,2400,2400,2400,2400,2400,2400,-1);
            this.dbHelper.insertPlace("Les Halles",50.669242,4.616339000000039,city,"010 45 03 34","Rue des Wallons 13, 1348 Louvain-La-Neuve","Taverne et café",1,1,830,830,830,830,830,1100,1500,2500,2500,2500,2500,2500,2500,2500);
            this.dbHelper.insertPlace("Quick",50.6696676,4.614369200000056,city,"010 81 48 39","Rue Charlemagne 42, 1348 Louvain-la-Neuve","Fastfood",1,0,1100,1100,1100,1100,1100,1100,1100,2500,2500,2500,2500,2500,2500,2500);
            this.dbHelper.insertPlace("Sushi World",50.6695569,4.612754499999937,city,"010 81 21 21","Rue Charlemagne 3, 1348 Ottignies-Louvain-la-Neuve","Traiteur spécialisé dans la préparation de sushi",1,0,1130,1130,1130,1130,1130,1130,-1,2200,2200,2200,2200,2200,2200,-1);
            this.dbHelper.insertPlace("La Boule de Chrystal",50.67277499999999,4.576866999999993,city,"010 41 25 95","Rue du Blanc-Ry 27, 1340 Ottignies-Louvain-la-Neuve","Restaurant et glacier",1,0,-1,1200,1200,1200,1200,1200,1200,-1,2030,2030,2030,2100,2100,2030);
            this.dbHelper.insertPlace("Brasserie de l'Esplanade",50.67176480000001,4.61728149999999,city,"010 81 68 37","L'esplanade 10, 1340 Ottignies-Louvain-la-Neuve","Brasserie à l'intérieur du centre commercial",1,1,1000,1000,1000,1000,1000,1000,-1,2000,2000,2000,2000,2100,2000,-1);
            this.dbHelper.insertPlace("La Popote Belge",50.66926299999999,4.6130640000000085,city,"010 24 41 80","Rue Rabelais 26, 1348 Ottignies-Louvain-la-Neuve","Brasserie à la cuisine typique de la Belgique",1,0,1200,1200,1200,1200,1200,1200,-1,2300,2300,2300,2300,2300,2300,-1);
            this.dbHelper.insertPlace("Pasta Follie's",50.66913099999999,4.612645899999961,city,"010 45 02 66","Agora 17, 1348 Ottignies-Louvain-la-Neuve","Cuisine italienne",1,0,1200,1200,1200,1200,1200,1830,-1,2230,2230,2230,2230,2230,2230,-1);
            this.dbHelper.insertPlace("Branche de figuier",50.66909500000001,4.613606000000004,city,"010 24 66 16","Grand-Rue 1, 1348 Ottignies-Louvain-la-Neuve","Cuisine palestinienne",1,0,800,800,800,800,800,800,-1,2500,2500,2500,2500,2500,2500,-1);
            this.dbHelper.insertPlace("Le Cambridge",50.66912000000001,4.6135020000000395,city,"010 45 13 22","Grand-Rue 3, 1348 Louvain-la-Neuve","Une décoration so british pour un bistrot belgo-français",1,1,900,900,900,900,900,900,1000,2500,2500,2500,2500,2500,2500,-1);
            this.dbHelper.insertPlace("EXKi",50.670061,4.614736499999935,city,"010 88 07 65","Place de L'université 10, 1348 Louvain-la-Neuve","EXKi propose des plats bio",1,0,730,730,730,730,730,730,-1,2100,2100,2100,2100,2100,2100,-1);
            this.dbHelper.insertPlace("Creperie bretonne",50.66857299999999,4.616150999999945,city,"010 45 15 85","Place des Brabançons 1, 1348 Louvain-la-Neuve","350 recettes de crêpes et un choix de 200 bières du pays dans un décor rustique",1,1,900,900,900,900,900,900,900,2500,2500,2500,2500,2500,2500,2500);
            this.dbHelper.insertPlace("Nulle Part Ailleurs",50.6691503,4.613964099999976,city,"010 45 13 27","Grand-Rue 9, 1348 Louvain-la-Neuve","Restaurant de qualité",1,0,1200,1200,1200,1200,1200,1200,1200,2200,2200,2200,2200,2200,2200,2200);
            this.dbHelper.insertPlace("Le Sablon",50.668541,4.614031000000068,city,"010 47 33 86","Rue du Sablon 11, 1348 Ottignies-Louvain-la-Neuve","Restaurant universitaire en self-service",1,0,1200,1200,1200,1200,1200,1200,-1,2030,2030,2030,2030,2030,2030,-1);
            this.dbHelper.insertPlace("Le Rabelais",50.6689285,4.6132456000000275,city,"010 45 29 60","Place Rabelais 38, 1348 Ottignies-Louvain-la-Neuve","Brasserie orientale",1,0,1200,1200,1200,1200,1200,1200,1200,2400,2400,2400,2400,2400,2400,2400);
            this.dbHelper.insertPlace("Le Cambodge",50.6689793,4.616696899999965,city,"010 45 03 27","Rue des Wallons 29, 1348 Louvain-la-Neuve","Restauration asiatique",1,0,1145,1145,1145,1145,1145,1145,1200,2300,2300,2300,2300,2300,2300,2300);
            this.dbHelper.insertPlace("Waffle Factory",50.669643,4.614193,city,"010 86 29 35","Rue Charlemagne 40, 1348 Louvain-la-Neuve","Gauffres, glaces et milkshakes à Louvain-la-Neuve",1,0,1000,1000,1000,1000,1000,1000,-1,1900,1900,1900,1900,1900,1900,-1);
            this.dbHelper.insertPlace("Baguettes d'or",50.6690596,4.613170500000024,city,"010 45 12 75","Agora 34, 1348 Ottignies-Louvain-la-Neuve","Cuisine asiatique",1,0,1200,1200,1200,1200,1200,1200,-1,2200,2200,2200,2200,2200,2200,-1);
            this.dbHelper.insertPlace("Cellule Sush'",50.6696752,4.615419100000054,city,"0496 03 54 44","Rue des Wallons 8, 1348 Louvain-la-Neuve","Restaurant spécialisé dans les sushis",1,0,1130,1130,1130,1130,1130,1130,-1,2200,2200,2200,2200,2200,2200,-1);
            this.dbHelper.insertPlace("Saveurs du Siam",50.6691933,4.612408500000015,city,"010 84 64 86","Agora 18, 1348 Louvain-la-Neuve","Cuisine thaïlandaise à consommer sur place ou à emporter",1,0,1145,1145,1145,1145,1145,1145,-1,2200,2200,2200,2200,2200,2200,-1);
            this.dbHelper.insertPlace("Le Piano",50.6712021,4.609261100000026,city,"010 45 07 92","Rue des Blancs Chevaux 7, 1348 Louvain-la-Neuve","Bons petits plats pour étudiants au budget raisonnable",1,0,1130,1130,1130,1130,1130,1130,1130,2330,2330,2330,2330,2330,2330,2330);
            this.dbHelper.insertPlace("Pizza Pasta",50.668485,4.616525000000024,city,"0483 30 26 46","Place des Wallons 24, 1348 Louvain-la-Neuve","Pizzas et pates en restauration rapide",1,0,1200,1200,1200,1200,1200,-1,1800,2230,2230,2230,2230,2230,-1,2230);
        }
        Intent bgService = new Intent(this, BGService.class);
        startService(bgService);
    }

    public String getSharedPreferenceString(){
        return sharedPreference;
    }

    public DBHelper getDbHelper(){
        return this.dbHelper;
    }

    public static synchronized EatNowApplication getAppInstance(){
        return appInstance;
    }


}