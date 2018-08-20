package com.example.daniel.solarsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    public ArrayList<Planet> arrayOfPlanets = new ArrayList<>();
    public final static String PLANET_POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Database database = new Database(this);

        database.clearDatabase();
        database = loadDataToDb(database);
        // Construct the data source and populate it
        try {
            arrayOfPlanets = database.getAllPlanets();
            database.closeDb();
        }catch (Exception e){
            e.printStackTrace();
            Log.i("DB-Info", "Database is empty! - MainActivity");
        }

        // Create the adapter to convert the array to views
        PlanetsAdapter adapter = new PlanetsAdapter(this, arrayOfPlanets);

        // Attach the adapter to a ListView
        ListView planetListView = findViewById(R.id.list_view_planet);
        planetListView.setAdapter(adapter);


        planetListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                int positionPressed = i+1;
                intent.putExtra(PLANET_POSITION,positionPressed);
                startActivity(intent);
            }
        });

    }

    public Database loadDataToDb(Database database){

        String[] planetNames = {"Merkury","Wenus","Ziemia","Mars","Jowisz","Saturn","Uran","Neptun"};

         int[] planetImages = {R.drawable.mercury, R.drawable.venus, R.drawable.earth, R.drawable.mars,
                R.drawable.jupiter, R.drawable.saturn, R.drawable.uranus, R.drawable.neptune};

         String[] planetInfo = {
                "Merkury – najmniejsza i najbliższa Słońcu planeta Układu Słonecznego." +
                        " Jako planeta wewnętrzna znajduje się dla ziemskiego obserwatora zawsze bardzo blisko Słońca," +
                        " dlatego jest trudna do obserwacji. Mimo to należy do planet widocznych gołym okiem i była znana już w starożytności." +
                        " Merkurego dojrzeć można jedynie tuż przed wschodem lub tuż po zachodzie Słońca." +
                        "Ukształtowanie powierzchni Merkurego przypomina Księżyc: są na nim liczne kratery uderzeniowe" +
                        " i praktycznie pozbawiony jest atmosfery. Temperatura powierzchni waha się od −183 °C do 427 °C." +
                        " W przeciwieństwie do Księżyca, planeta ma jednak duże żelazne jądro," +
                        " generujące pole magnetyczne o natężeniu stukrotnie mniejszym od natężenia ziemskiego pola magnetycznego." +
                        " Wielkość jądra powoduje, że Merkury ma jedną z największych gęstości spośród planet Układu Słonecznego (nieznacznie większą ma Ziemia)." +
                        " Merkury nie ma naturalnych satelitów.",

                "Wenus – druga pod względem odległości od Słońca planeta Układu Słonecznego." +
                        " Jest trzecim pod względem jasności ciałem niebieskim widocznym na niebie," +
                        " po Słońcu i Księżycu. Jej obserwowana wielkość gwiazdowa sięga −4,6m i jest wystarczająca," +
                        " aby światło odbite od Wenus powodowało powstawanie cieni." +
                        " Ponieważ Wenus jest bliżej Słońca niż Ziemia, zawsze jest widoczna w niewielkiej odległości kątowej od niego;" +
                        " jej maksymalna elongacja to 47,8°. Odległość Wenus od Ziemi zmienia się w zakresie od ok. 40 mln km do 259 mln km." +
                        "Nazwa planety wzięła się od rzymskiej bogini miłości, Wenus." +
                        " Na niebie planeta jest widoczna tylko przez około trzy godziny przed wschodem Słońca" +
                        " nad wschodnim horyzontem lub po zachodzie Słońca nad zachodnim horyzontem." +
                        " Nieodłączna towarzyszka wschodzącego i zachodzącego Słońca," +
                        " nazywana jest także Gwiazdą Poranną, Zaranną, Porankową czyli Jutrzenką (łac. Stella Matutina)," +
                        " kiedy zwiastuje wschód Słońca, albo Gwiazdą Wieczorną, która finalizuje jego zachód.",

                "Ziemia – trzecia, licząc od Słońca, oraz piąta pod względem wielkości planeta Układu Słonecznego. " +
                        "Pod względem średnicy, masy i gęstości jest to największa planeta skalista Układu Słonecznego. " +
                        "Ziemia jest zamieszkana przez miliony gatunków, w tym przez człowieka. Jest jedynym znanym miejscem we Wszechświecie," +
                        " w którym występuje życie. Według danych zebranych metodą datowania izotopowego, planeta uformowała się ok. 4,54 ± 0,05 mld lat temu." +
                        "W ciągu pierwszego miliarda lat po uformowaniu się Ziemi wewnątrz jej oceanów pojawiło się życie." +
                        " Z żyjących na Ziemi organizmów żywych składa się biosfera, która wpływa na jej atmosferę, hydrosferę," +
                        " litosferę i inne czynniki abiotyczne planety, umożliwiając rozwój i wzrost liczby organizmów aerobowych" +
                        " i anaerobowych oraz powstanie ozonosfery. Rozwój życia na lądzie i w wodzie umożliwiła powłoka ozonowa" +
                        " oraz ziemskie pole magnetyczne, zmniejszając natężenie promieniowania ultrafioletowego, oraz magnetosfera," +
                        " odbijająca cząstki wiatru słonecznego i promieniowania kosmicznego. Dystans dzielący Słońce od Ziemi," +
                        " jej właściwości fizyczne oraz jej historia geologiczna są najważniejszymi czynnikami," +
                        " które pozwoliły organizmom żyć i ewoluować. Różnorodność biologiczna Ziemi nieustannie powiększa się," +
                        " chociaż w dziejach życia Ziemi proces ten był kilkukrotnie przerywany, kiedy miało miejsce masowe wymieranie gatunków." +
                        " Pomimo że naukowcy szacują, że ok. 99% gatunków organizmów żywych (ok. 5 mld) kiedykolwiek zamieszkujących" +
                        " Ziemię uważa się za wymarłe, wciąż mieszka na niej ok. 10–14 mln gatunków, z czego 1,2 mln zostało udokumentowanych.",

                "Mars – czwarta według oddalenia od Słońca planeta Układu Słonecznego." +
                        " Nazwa planety pochodzi od imienia rzymskiego boga wojny – Marsa." +
                        " Zawdzięcza ją swej barwie, która przy obserwacji z Ziemi wydaje się rdzawo-czerwona" +
                        " i kojarzyła się starożytnym z pożogą wojenną. Postrzegany odcień wynika stąd," +
                        " że powierzchnia planety zawiera tlenki żelaza. Mars jest planetą wewnętrzną z cienką atmosferą," +
                        " o powierzchni usianej kraterami uderzeniowymi, podobnie jak powierzchnia Księżyca." +
                        " Występują tu także inne rodzaje terenu, podobne do ziemskich: wulkany, doliny, pustynie i polarne czapy lodowe." +
                        " Okres obrotu wokół własnej osi jest niewiele dłuższy niż Ziemi i wynosi 24,6229 godziny (24 h 37 min 22s)." +
                        " Na Marsie znajduje się najwyższa góra w Układzie Słonecznym – Olympus Mons i największy kanion – Valles Marineris." +
                        " Gładki obszar równinny Vastitas Borealis na półkuli północnej obejmuje 40% powierzchni planety" +
                        " i może być pozostałością ogromnego uderzenia. W przeciwieństwie do Ziemi, Mars jest geologicznie i tektonicznie nieaktywny.",

                "Jowisz – piąta w kolejności oddalenia od Słońca i największa planeta Układu Słonecznego[b]." +
                        " Jego masa jest nieco mniejsza niż jedna tysięczna masy Słońca," +
                        " a zarazem dwa i pół razy większa niż łączna masa wszystkich innych planet w Układzie Słonecznym." +
                        " Wraz z Saturnem, Uranem i Neptunem tworzy grupę gazowych olbrzymów, nazywaną czasem również planetami jowiszowymi." +
                        "Planetę znali astronomowie w czasach starożytnych, była związana z mitologią i wierzeniami religijnymi wielu kultur." +
                        " Rzymianie nazwali planetę na cześć najważniejszego bóstwa swojej mitologii – Jowisza." +
                        " Obserwowany z Ziemi Jowisz może osiągnąć jasność do −2,95m." +
                        " Jest to trzeci co do jasności naturalny obiekt na nocnym niebie po Księżycu i Wenus (okresowo," +
                        " w momencie wielkiej opozycji, jasnością może mu dorównywać Mars).",

                "Saturn – gazowy olbrzym, szósta planeta Układu Słonecznego pod względem odległości od Słońca," +
                        " druga po Jowiszu pod względem masy i wielkości. Charakterystyczną jego cechą są pierścienie," +
                        " składające się głównie z lodu i w mniejszej ilości z odłamków skalnych; inne planety-olbrzymy także mają systemy pierścieni," +
                        " ale żaden z nich nie jest tak rozległy ani tak jasny. Według danych z marca 2015 roku znane są 62 naturalne satelity Saturna." +
                        "Promień Saturna jest około 9 razy większy od promienia Ziemi." +
                        " Chociaż jego gęstość to tylko jedna ósma średniej gęstości Ziemi, ze względu na wielokrotnie większą objętość masa Saturna" +
                        " jest dziewięćdziesiąt pięć razy większa niż masa Ziemi.",

                "Uran − gazowy olbrzym, siódma w kolejności od Słońca planeta Układu Słonecznego." +
                        " Jest także trzecią pod względem wielkości i czwartą pod względem masy planetą naszego systemu." +
                        " Nazwa planety pochodzi od Uranosa, który był bogiem i uosobieniem nieba w mitologii greckiej (klasyczna greka: Οὐρανός)," +
                        " ojcem Kronosa (Saturna) i dziadkiem Zeusa (Jowisza). Choć jest widoczny gołym okiem[b] podobnie jak pięć innych planet," +
                        " umknął uwadze starożytnych obserwatorów ze względu na niską jasność i powolny ruch po sferze niebieskiej." +
                        " Sir William Herschel ogłosił odkrycie planety w dniu 13 marca 1781, po raz pierwszy w historii nowożytnej rozszerzając znane granice Układu Słonecznego." +
                        " Uran to również pierwsza planeta odkryta przy pomocy teleskopu.",

                "Neptun – gazowy olbrzym, ósma, najdalsza od Słońca planeta w Układzie Słonecznym." +
                        " Nazwa planety pochodzi od rzymskiego boga mórz Neptuna." +
                        " Wśród planet Układu Słonecznego jest czwartą pod względem średnicy i trzecią pod względem masy." +
                        " Neptun jest ponad 17 razy masywniejszy od Ziemi i trochę masywniejszy od swojego bliźniaka, Urana," +
                        " który ma masę prawie 15 razy większą od masy Ziemi. Krąży wokół Słońca w odległości około 30 razy większej" +
                        " niż dystans Ziemia-Słońce. Jego symbol astronomiczny to Neptune symbol.svg, stylizowana wersja trójzębu Neptuna." +
                        "Odkryty 23 września 1846[4] Neptun jest jedyną planetą Układu Słonecznego," +
                        " której istnienie wykazano nie na podstawie obserwacji nieba, ale na drodze obliczeń matematycznych." +
                        " Niespodziewane zmiany w ruchu orbitalnym Urana doprowadziły astronomów do wniosku," +
                        " że podlega on perturbacjom pochodzącym od nieznanej planety." +
                        " Neptun został następnie zaobserwowany przez Johanna Galle w miejscu przewidzianym" +
                        " przez Urbaina Le Verriera, a wkrótce został też odkryty jego największy księżyc, Tryton; żaden" +
                        " z pozostałych 13 znanych dziś księżyców Neptuna nie został odkryty za pomocą teleskopu aż do XX wieku." +
                        " Neptun został odwiedzony przez tylko jedną sondę kosmiczną Voyager 2," +
                        " która przeleciała w pobliżu planety 25 sierpnia 1989 roku."
        };

        String[] planetLink = {
                "https://pl.wikipedia.org/wiki/Merkury",
                "https://pl.wikipedia.org/wiki/Wenus",
                "https://pl.wikipedia.org/wiki/Ziemia",
                "https://pl.wikipedia.org/wiki/Mars",
                "https://pl.wikipedia.org/wiki/Jowisz",
                "https://pl.wikipedia.org/wiki/Saturn",
                "https://pl.wikipedia.org/wiki/Uran",
                "https://pl.wikipedia.org/wiki/Neptun"
        };

        for(int i = 0; i<planetNames.length; i++){

            database.addRecord(planetNames[i],planetImages[i],planetInfo[i],planetLink[i]);

        }

        return database;
    }

}
