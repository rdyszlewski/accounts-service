package com.farfocle.accountsservice.validation;

import com.farfocle.accountsservice.password_validator.PasswordData;
import com.farfocle.accountsservice.password_validator.ValidationResult;
import com.farfocle.accountsservice.password_validator.exceptions.NullPasswordException;
import com.farfocle.accountsservice.password_validator.rules.MaxLengthRule;
import com.farfocle.accountsservice.password_validator.rules.MinLengthRule;
import com.farfocle.accountsservice.password_validator.rules.Rule;
import com.farfocle.accountsservice.validation.exceptions.password.PasswordException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordValidatorComparisionTest {

    private final String TEXT = "W jednej chwili spacerujesz po krakowskiej kamienicy, aby zaraz potem uciekać przed makabrycznym potworem w mrocznym wymiarze. Najpierw przeglądasz dokumenty dotyczące Solidarności, a później przeszukujesz stertę książek poświęconych przywoływaniu demonów. Specyficzne zderzenie dwóch światów to The Medium - najnowsza polska gra z zaskakująco dobrą intrygą. Krakowskie studio Bloober Team pozostaje wierne swoim korzeniom, tworząc grę o międzynarodowym kalibrze, z zachowaniem polskiej specyfiki oraz perspektywy. Jeśli wcześniej kogokolwiek mogło razić pozyskiwanie publicznych środków na produkcję gier wideo, Blooberzy odpłacają się podatnikowi w dwójnasub. W The Medium jest w zasadzie wszystko, co dotyczy naszej historii najnowszej: PRL i PZPR, Solidarność, okupacja okresu drugiej wojny światowej i codzienna szarość Europy Środkowej lat 90.Weźcie typową polską meblościankę. Dodajcie do tego obskurną kamienicę na jednym z osiedlowych blokowisk. Nie zapomnijcie o białych koronkowych ozdobach i maszynce do mięsa, będącej stałym elementem wystroju większości polskich kuchni. Ta nasza szara post-transformacyjna rzeczywistość lat 90. jest w The Medium niezwykle silna. Zwłaszcza w początkowej fazie gry. Ależ jestem ciekaw, jak zachodni odbiorca będzie reagował na wizytę w takim stereotypowym polskim mieszkaniu z 1999 r. Jak odnajdzie się w tym nieco smutnym otoczeniu, uchodzącym za życiowy standard wielu z nas. Polska lat 90. jest fascynującym obrazem sama w sobie, ale Bloober Team dodaje do tego pejzażu silnym element świata nadprzyrodzonego. Oto bowiem główna bohaterka The Medium - Marianna - posiada zdolności nadprzyrodzone, wyrażone m.in. w zdolności widzenia zmarłych. Marianna zawsze stara się koić niespokojne dusze, odsyłając je w zaświaty. Co czasem nie jest łatwe, gdy np. ofiara zabójstwa koniecznie chce się przytulić, a z jej czaszki wycieka mózg. The Medium nie boi się pokazywać scen nieprzyjemnych i niepokojących, ale nigdy nie epatuje przesadnie brutalnością czy makabrycznością. Marianna - niczym egzorcysta John Constantine - od lat korzysta ze swojego daru. Życie bohaterki wywraca się jednak do góry nogami, gdy umiera jej ojczym, a w dniu pogrzebu ktoś tajemniczy dzwoni, aby obwieścić, że dokładnie zna jej dar, a także wie wszystko o samej Mariannie. W ten sposób The Medium rozpoczyna podróż w przeszłość bohaterki, zamieniając się w niemal modelową przygodówkę starej daty, z ujęciami realizowanymi w formie stałych kadrów. Trochę jak w pierwszych odsłonach Resident Evil oraz Silent Hill.W The Medium pojawiają się zagadki logiczne, które musimy rozwiązać, aby pchnąć fabułę do przodu. Przesuwamy wajchy, przełączamy dźwignie, łączymy rozbite elementy lustra czy wypompowujemy wodę z podziemnych korytarzy. Klasyka gatunku, ale silnie doprawiona wizytami na tamten świat, o czym opowiem w następnych akapitach. Jeśli uwielbialiście odkrywać komisariat policji z Resient Evil czy podróżować do szkole w Silent Hill, od razu poczujecie się jak w domu. The Medium to dokładnie ten sam patent na rozgrywkę. Z drugiej strony, w przeciwieństwie do RE czy SH, The Medium nie oferuje wystandaryzowanych sekwencji walki. Nie zrozumcie mnie źle - w grze wielokrotnie poczujemy obecność demonów i potworów. Jednak zamiast strzelać do nich z policyjnego pistoletu, zazwyczaj musimy uciekać, wykorzystywać elementy otoczenia bądź rozprawiać się z przeciwnikami za pomocą sprytnych pułapek. Wszystko to silnie oskryptowane sceny, stanowiące potrzebne przełamanie dosyć spokojnej eksploracji. Bardzo ciekawym pytaniem jest, czy The Medium budzi grozę. Jeśli przez grozę rozumiemy konieczność zmieniania pampersa niczym w przerażającym Silent Hill 2 lub P.T., to polska gra znajduje się na przeciwległym biegunie. Bliżej jej do takich dzieł jak Siódmy Zmysł, Labirynt Fauna czy wcześniej wspomniany John Constantine. The Medium okazjonalnie BYWA straszne, ale tylko dla kogoś, kto reaguje alergicznie na jakiekolwiek horrory. Dla pozostałych graczy polska produkcja będzie momentami niepokojąca czy nieprzyjemna, ale to bardziej psychologiczny thriller niż dojmujący horror.Pamiętam, gdy dekady temu powstawała pierwsza wersja Resident Evil 4. Mistrzowie horroru z Capcomu mieli wtedy wizję, aby rozgrywka odbywała się na dwóch płaszczyznach jednocześnie: tej rzeczywistej oraz drugiej, zbudowanej na podstawie środków psychoaktywnych powodujących wizje i omamy. Gracz miał się swobodnie przełączać między tymi dwoma światami, robiąc to w okamgnieniu. Takie lawirowanie między wymiarami w czasie rzeczywistym byłoby kluczowe, aby pokonać niektórych przeciwników lub przed nimi uciec. Ostatecznie zaawansowany projekt Capcomu porzucono, ponieważ ówczesne konsole nie były w stanie zrealizować ambitnej wizji. Hardware nie pozwalało na płynne renderowanie dwóch światów jednocześnie. Przed dokładnie tym samym problemem stanęli producenci z Bloober Team. Ich The Medium miało pierwotnie zadebiutować lata temu. Jednak unikalny patent na rozgrywkę, zakładający podróżowanie po dwóch rzeczywistościach jednocześnie, nie pozwalał zaoferować przygody na odpowiednio płynnym, odpowiednio atrakcyjnym wizualnie poziomie. Zmieniło się to wraz z premierą konsol nowej generacji. Dlatego The Medium debiutuje na przepotężnym Xboksie Series.Jednak nawet na używanym przeze mnie Xboksie Series X przygodówka potrafi na moment zaciąć konsolę. Do tego podczas jednoczesnego przemierzania dwóch wymiarów miałem wrażenie, że rozdzielczość natywna ulega odczuwalnemu obniżeniu. Obiekty stają się mniej ostre, a obraz nie jest już tak klarowny. Nie byłoby to nic zaskakująco, bo właśnie w ten sposób działa często tzw. split-screen, stosowany np. w grach wyścigowych umożliwiających ściganie się dwóch graczy przed ekranem jednego telewizora. W praktyce jedna rozgrywka w dwóch wymiarach to ciekawy, ożywczy i dobrze wdrożony pomysł. Taka podwójna eksploracja jest jednocześnie bardzo angażująca i wymusza silną koncentrację gracza. Na szczęście krakowscy twórcy zachowali umiar, urozmaicając podróże między wymiarami tradycyjną eksploracją rzeczywistego świata. Jest kiedy odpocząć. Nie czujemy się przytłoczeni i nigdy nie mamy wrażenia, że producenci przesadnie epatują swoim unikalnym pomysłem na rozgrywkę. Odpowiednie proporcje zostały zachowane, za co należy się studio uznanie.Z perspektywy snutej narracji, warstwy dźwiękowej czy odpowiedniego tempa rozgywki, The Medium to faktycznie najciekawsza, najlepsza i najbardziej dopracowana produkcja krakowskiego studia. Jednocześnie nic nie mogę poradzić na fakt, że nieco lepiej bawiłem się w przygodowym The Observer od tego samego zespołu deweloperskiego. Przypuszczam, że może to być zasługa mniejszej linearności Observera, a także wyposażenia go w dodatkowe, opcjonalne zadania oraz wątki. The Medium jest bardziej liniowe. Idziemy jak po sznurku, a nasza swoboda sprowadza się do tego, czy podczas przemykania od osłony do osłony zaalarmowaliśmy potwora (jeśli nie, dostaniemy specjalne osiągnięcie). Po cichu liczyłem, że Bloober pozostawi nieco miejsca na oderwane od głównej osi fabularnej wątki, np. problemy duchów znajdujących się w potrzebie. Tak się jednak nie dzieje, a my nie mamy już po co wracać do raz pokonanej gry. Oczywiście, o ile nie jesteśmy łowcą trofeów. Dziwi mnie również, że Bloober Team nie oferuje graczom polskiej wersji językowej. Zwłaszcza, że w grze pojawia się sam Marcin Dorociński. Ileż bym dał, aby go usłyszeć podczas swojej przygody. Angielskie dialogi są niezłe, ale Dorociński jest bezcenny. Odgrywana przez niego postać ma kluczowe znaczenie dla fabuły, stanowi ciekawą odskocznię od problemów głównej bohaterki i wprowadza do The Medium zupełnie nową perspektywę. To jeden z najlepszym elementów krakowskiej produkcji.Grając w The Medium nie mogłem odeprzeć wrażenia, że polski tytuł jest trochę jak Red Dead Redemption 2 - to bardziej gra od twórców dla twórców niż dla graczy. Ten projekt latami wisiał krakowskiemu studio na agendzie, stanowiąc zbiór niezrealizowanych pomysłów, ambicji i obietnic. Dzięki postępowi technologicznemu The Medium nareszcie mogło stać się faktem, ale jednocześnie część oryginalnych składowych bardzo się zestarzała. Przez to przygodówka przypomina nieco grę z lat 90., którą ktoś postanowił odrestaurować w 2021 r. ";

    @Autowired
    private PasswordValidator validator;

    @Test
    public void test() throws PasswordException  {
        String password = "a";
        List<Rule> rules = Arrays.asList(
                new MinLengthRule(8),
                new MaxLengthRule(20)
        );
        com.farfocle.accountsservice.password_validator.PasswordValidator validator = new com.farfocle.accountsservice.password_validator.PasswordValidator(rules);
        ValidationResult result = validator.validate(new PasswordData(password));
        System.out.println(result);
//        RulePasswordValidator ruleValidator = new RulePasswordValidator();
//        long start = System.nanoTime();
//        ruleValidator.validate(TEXT);
//        long time = System.nanoTime() - start;
//        System.out.println("Passay - " + time/ 1000000);
//        start = System.nanoTime();
//        try{
//            validator.validate(TEXT);
//        }catch (Exception e){
//
//        }
//        time = System.nanoTime() - start;
//        System.out.println("Mojaaa - " + time/ 1000000);
    }
}
