# zadanie 1 
  wykszKob <- 220
  kob <- 520
  wykszMez <- 165
  mez <- 480
  pKob <- wykszKob / kob
  pMez <- wykszMez / mez
  probWyksz <- (wykszKob + wykszMez) / (kob + mez)
  deviation <- sqrt(probWyksz * (1 - probWyksz) * (1 / kob + 1 / mez))

# a ) #wartość oczekiwana w tym przypadku jest 0 
# (testujemy czy nie ma zależności)
# H0 to sytuacja w której odchylenie wynosi 0
  
  p <- 2 * pnorm(((pMez - pKob) - 0) / deviation)
  prop.test(c(220, 165), c(520, 480))

# teza, że jest tyle samo kobiet i mężczyzn z wyższym wykształceniem 
# jest fałszywa (mamy mocne dowody przeciw)
  
# b ) 
  tab <- matrix(c(kob - wykszKob, mez - wykszMez, wykszKob, wykszMez), nrow=2)
  dimnames(tab) <- list(Plec = c("Kobiety", "Mezczyzni"), 
                        Wyksztalcenie = c("Brak Wyzszego", "Wyzsze"))
tab

# c )
  chisq.test(tab)
  fisher.test(tab)  
  
# nadal stwierdza że teza H0 jest słaba kto by przypuszczał
  
# d )
  # H0 - oczkiwany wzrost kobiet i mężczyzn jest taki sam
  wzrostMez <- 174
  warWzrMez <- 121
  wzrostKob <- 166
  warWzrKob <- 100
  
  odchylWynikowe <- sqrt(warWzrMez / mez + warWzrKob / kob)
  
  #testujemy naszą hipotezę
  p <- 2 * pnorm((wzrostKob - wzrostMez) / odchylWynikowe)
  
  #ta hipoteza jest całkiem nieprawdopodobna
  
  
# odczytanie pliku z wagami
  file <- read.csv2("../waga1.csv")

kob <- sum(file$plec == 1)
mez <- sum(file$plec == 0)
  
# zadanie 2 czy studenci przytyli 2kg
# H0 - waga po studiach jest średnio większa o 2kg
  t.test(file$Waga_po, file$Waga_przed, mu = 2, paired=TRUE)
# p value jest niskie więc hipoteza nie jest prawdziwa, 
  
# zadanie 3 
  # H0 -proporcja kobiet ważących więcej niż 70kg po studiach i
  # mężczyzn spełniających ten warunek jest taka sama
  
  wiecejNiz70Mez <- sum(file$Waga_po > 70 & file$plec == 0)
  wiecejNiz70Kob <- sum(file$Waga_po > 70 & file$plec == 1)
  
  table <- matrix(c(wiecejNiz70Mez, mez - wiecejNiz70Mez, 
                    wiecejNiz70Kob, kob - wiecejNiz70Kob), nrow=2)
  chisq.test(table)

  # p jest małe więc mamy mocne dowody że hipoteza jest nieprawdziwa  
  
# zadanie 4
  # H0 - średnia różnica między wzrostem kobiet i mężczyzn wynosi 5cm
  t.test(file$Wzrost[file$plec == 0], file$Wzrost[file$plec == 1], md=2)
  # p jest bardzo małe co oznacza, że hipoteza jest bardzo nieprawdopodobna
  
# zadanie 5
  # H0 - 80% studentów przybrało na wadze
  studenciZgrubli <- sum(file$Waga_po > file$Waga_przed)
  prop.test(studenciZgrubli, length(file$Waga_przed), p=0.8)  
  # nie mamy dowodów przeciwko H0, ale prawdopodobnie jest nieprawidłowe    

# zadanie 6
  # H0 - studenci męscy przytyli o 4kg
  vctrMez <- file$plec == 0
  t.test(file$Waga_po[vctrMez], file$Waga_przed[vctrMez], md=4, paired=TRUE)  
  # mamy mocne argumenty że H0 jest nieprawdziwe i różnica nie wynosi 4kg
  
  
  