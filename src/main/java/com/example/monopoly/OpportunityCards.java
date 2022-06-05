package com.example.monopoly;

import java.util.Random;

public class OpportunityCards {
	String name;
	Integer pay;
	Integer get;
	Integer whereToGo;
	Boolean prisonExit;
	Boolean isChance;
	
	String chances[] = {
			"Przejdź do biblioteki",
			"Idz na start (odbierz 200 $)",
			"Idz do Katedra Biotechnologii Środowiskowej. Jeśli przejdziesz przez Start, odbierz 200 $.",
			"Idz do Instytut Fizyki. Jeśli przejdziesz przez Start, odbierz 200 $.",
			"Bank wypłaca ci dywidendę w wysokości 50$.",
			"Wyjdź z więzienia za darmo",
			"Cofnij się o 3 miejsca.",
			"Idź do więzienia. Idź bezpośrednio do więzienia, nie odbierz 200$ za przejście przez Start.",
			"Wykonaj generalne naprawy wszystkich swoich nieruchomości. Za każdą posiadłóść zapłać 25$.",
			"Mandat za przekroczenie prędkości 15$.",
			"Wybierz się na wycieczkę po Centrum Sportu. Jeśli przejdziesz przez Start, odbierz 200$.",
			"Zostałeś wybrany na przewodniczącego rady nadzorczej. Odbierz 50$.",
			"Twój kredyt na budowę został zrealizowany. Odbierz 150$."
	};
	
	Integer chancesGet[] = {
			0, 0, 0, 0, 50, 0, 0, 0, 0, 0, 50, 150
	};
	
	Integer chancesPay[] = {
			0, 0, 0, 0, 0, 0, 0, 0, 25, 15, 0, 0, 0
	};
	
	Integer chancesWhereToGo[] = {
			20, 0, 18, 37, null, null, null, 30, null, null, 5, null, null
	};
	
	String community[] = {
			"Zaliczka na wyjazd (Zbierz £200)",
			"Błąd banku na Twoją korzyść. Zbierz 200 funtów",
			"Opłata za wizytę u lekarza. Zapłać 50 funtów.",
			"Ze sprzedaży zapasów otrzymujesz 50 funtów.",
			"Wyjdź z więzienia za darmo",
			"Idź do więzienia. Idź bezpośrednio do więzienia, nie zdaj egzaminu Idź, nie zbieraj £200",
			"Fundusz wakacyjny dojrzewa. Otrzymujesz 100 funtów",
			"Zwrot podatku dochodowego. Odbierz 20 funtów.",
			"Są Twoje urodziny. Odbierz 10 funtów od każdego gracza.",
			"Ubezpieczenie na życie staje się wymagalne. Zbierz 100 funtów.",
			"Opłać opłaty szpitalne w wysokości £100",
			"Opłać czesne w szkole w wysokości 50 funtów.",
			"Otrzymaj 25 funtów opłaty za doradztwo",
			"Zdobyłeś drugą nagrodę w konkursie piękności. Zbierz 10 £",
			"Odziedziczyłeś 100 funtów"

	};
	
	Integer communityGet[] = {
			200, 200, 0, 50, 0, 0, 100, 20, 10, 100, 0, 0, 25, 10, 100
	};
	
	Integer communityPay[] = {
			0, 0, 50, 0, 0, 0, 0, 0, 0, 0, 100, 50, 0, 0, 0
	};
	

	OpportunityCards(Boolean isChance){
		Random rand = new Random();
		
		if (isChance) {
			int x = rand.nextInt(chances.length - 1);
			this.name = chances[x];
			this.pay = chancesPay[x];
			this.get = chancesGet[x];
			this.whereToGo = chancesWhereToGo[x];
			this.prisonExit = false;
			if(x == 5) {
				this.prisonExit = true;
			}
			this.isChance = true;
		}
		else {
			int x = rand.nextInt(community.length - 1);
			this.name = community[x];
			this.pay = communityPay[x];
			this.get = communityGet[x];
			this.whereToGo = null;
			if(x == 5) {
				this.whereToGo = 30;
			}
			this.prisonExit = false;
			if(x == 4) {
				this.prisonExit = true;
			}
			this.isChance = false;
		}
	}
	
	
	
}
