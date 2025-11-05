
#zadanie 1
Imie <- c("Krzysztof", "Maria", "Henryk", "Anna")
Plec <- c("m", "k", "m", "k")
Analiza <- c(3.5, 4.5, 5.0, 4.5)
Algebra <- c(4.0, 5.0, 4.0, 3.5)
frame1 <- data.frame(Imie, Plec, Analiza, Algebra) #pierwsza ramka
frame1[1:2,]
str(frame1)
avgGrade <- sum(frame1$Analiza) / length(frame1$Analiza)
meanGrade <- mean(frame$Analiza)
frame1$avgGrades <- (frame1$Analiza + frame1$Algebra) / 2
frameKobiety <- frame1[frame1$Plec == "k",]
frameDobreOceny <- frame1[frame1$Analiza >= 4.5 | frame1$Algebra>=4.5,]
countDobraAnaliza <- length(frame1[frame1$Analiza >= 4.5, ]$Analiza)

#zadanie 2
data <- read.csv2("../waga1.csv")
head(data, 5)
str(data)
sum(data$Waga_przed) / length(data$Waga_przed)
sum(data$Wzrost) / length(data$Wzrost)
data$Wspolczynnik <- data$Waga_przed / (data$Wzrost * data$Wzrost) * 10000
fatWomen <- data[data$Wspolczynnik > 25 & data$plec == 1,]
men <- data[data$plec == 0,]
length(data[data$Wzrost > 175, "Wzrost"])

#zadanie 3
data <- read.csv2("../mieszkania.csv")
head(data)
str(data)
sum(data$Metraz) / length(data$Metraz)
sum(data$Cena) / length(data$Cena)
data$CenaZaMetr <- data$Cena / data$Metraz
psiok <- data[data$Dzielnica == "Psie Pole" & data$Cena < 400000,]
srodmiescie <- data[data$Dzielnica == "Srodmiescie" & data$Metraz > 60,]
stablePrices <- data.frame(unique(data$Dzielnica), sapply(unique(data$Dzielnica), function(x) sd(data[data$Dzielnica == x, "CenaZaMetr"])))
bestPrice <- data[which.max(data$Metraz / data$Pokoje / data$Cena),]



