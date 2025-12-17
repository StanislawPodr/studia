# zadanie 1
  avgIQ <- 109
  varIQ <- 225
  sampleStudents <- 100
  noStudentMore115 <- 30
  p <- 0.3
  # a) test Z
    #przybliżanie rozkładu dwumianowego dla 100 
    #prób rozkład dla 0.35 jako wartości
    #oczekiwanej. Sprawdzamy jakie jest prawdopodobieństwo na to że wylosujemy 
    #rezultat który rzeczywiście otrzymaliśmy
    probForMore115Hypothesis <- 0.35
    p_a <- 2*pnorm(
      noStudentMore115 / sampleStudents, 
      probForMore115Hypothesis, 
      sqrt(probForMore115Hypothesis * (1 - probForMore115Hypothesis) 
           / sampleStudents)
    )
    # nie trzeba odejmować od 1 bo to już nasz ogon (0.3 wypada przed wartością
    # oczekiwaną 0.35)
    p_a <- prop.test(noStudentMore115, sampleStudents, probForMore115Hypothesis)
  
  
  # b) wyliczamy sobie jakiś promień odchylenia
    z_b <- qnorm(0.995)
    res <- z_b * sqrt(p * (1 - p) / sampleStudents)
    
    prop.test(x = noStudentMore115, n = sampleStudents, conf.level = 0.99)
    
    
  # c) wyliczanie tego samego tylko dla średniego IQ studentów (dla 90%)
    z_c <- qnorm(0.95)
    #wartość oczekiwana to średnia, czyli 109, a znormalizowane odchylenie
    res <- z_c * sqrt(varIQ/sampleStudents)
  
  # d) dla testu studenta
    t_d <- qt(0.95, sampleStudents - 1)
    res <- t_d * sqrt(varIQ/sampleStudents)
  
  # e) test czy średni IQ wynosi 115, generujemy rozkład z wartością oczekiwaną
    # 115 i sprawdzamy jaka szansa jest na naszą próbę. Zakładamy że odchylenie
    #będzie wynikało z wariancji. Oczywiście dzielimy przez pierwiastek z próby
    #bo zakładamy średnią z obserwacji powtórzonej 100 razy, aby zobaczyć jej
    #prawdopodobieństwo na podstawie naszej obserwacji
    res <- 2*pnorm(
          avgIQ,
          115,
          sqrt(varIQ / sampleStudents) 
    )
    
    #test t studenta
    t_e <- sqrt(sampleStudents) * (avgIQ - 115) / sqrt(varIQ) # jest ujemne
    #więc spoko
    res <-2*pt(t_e, sampleStudents)
    # niezbyt prawdopodobne :)
    
#zadania 2-7
  file <- read.csv2("../waga1.csv")

#zadanie 2 
  #czy średni wzrost wynosi 170cm?
  t.test(file$Wzrost, mu = 170)
  # no wychodzi że chyba nie

#zadanie 3 - przedział ufności dla wzrostu i pewności średniej 90%
  t.test(file$Wzrost, conf.level = 0.9)

#zadanie 4 test czy średnia wzrostu studentek to 160cm
  t.test(file$Wzrost[file$plec == 1], mu=160)
  # no raczej nie jest
  file$Wzrost[file$plec == 1]

#zadanie 5 przedział ufności wzrostu kobiet 98%
  t.test(file$Wzrost[file$plec == 1], conf.level = 0.98)
  
#zadanie 6 ile mężczyzn wyższych niż 180cm
  prop.test(length(which(file$Wzrost[file$plec == 0] > 180)), 
            length(file$Wzrost[file$plec == 0]), 0.25)

#zadanie 7 wyznaczanie dla poziomu zgodności na 96%
  prop.test(length(which(file$Wzrost[file$plec == 0] > 180)), 
            length(file$Wzrost[file$plec == 0]), conf.level = 0.96)
  