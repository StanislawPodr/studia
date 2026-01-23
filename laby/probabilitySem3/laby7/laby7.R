# zadanie 1 - rzuty kostką
realThrows <- matrix(c(1, 171, 2, 200, 3, 168, 4, 213, 5, 226, 6, 222), nrow=2)

sumOfThrows <- 0
for (col in 1:ncol(realThrows)) {
  sumOfThrows <- sumOfThrows + realThrows[2, col]
}

# a
expectedThrows <- matrix(c(1:6, rep(sumOfThrows / 6, 6)), ncol=6, byrow=TRUE)

# b testowanie tego
testVal <- 0
for (realisation in realThrows[2,]) {
  testVal <- testVal + (realisation - 200) ** 2 / 200
}

# c wyznaczanie p-value (rozkład w przybliżeniu chi-kwadrat)
pchisq(testVal, 5, lower.tail = FALSE)


# d
#wniosek jest taki, że prawdopodobieństwo na to że jest to rozkład rzutów jest
#niewielkie

#e to samo z chisq.test
chisq.test(realThrows[2,], p=rep(1/6, 6))



# zadanie 2 - test ruskich

# a 
# 1000 liczb z rozkładu wykładniczego. 
x <- rexp(1000, rate=1)

# b
# H0 -> czy x pochodzi z rozkładu normalnego z wartością oczekiwaną 1 i 
# odchyleniem 1
ks.test(x, "pnorm", mean=1, sd=1)
# odrzucamy H0 bo p jest śmiesznie niskie

# H0-> czy x pochodzi z rozkładu wykładniczego z lamda równym 1
ks.test(x, "pexp", rate=1)
# no wskazuje p value że pochodzi i to prawda bo z tego losowaliśmy

# c
# 1000 prób z rozkładu gamma
x <- rgamma(1000, shape=100, scale=1)

# d 
# H0 -> czy x pochodzi z rozkładu normalnego z wartością oczekiwaną 100 i 
# odchyleniem 10
ks.test(x, "pnorm", mean=100, sd=10)
# wychodzi różnie ale moglibyśmy stwierdzić że pochodzi 
# (rozkład normalny też jest gamma), nie odrzucamy

# H0-> czy x pochodzi z rozkładu gamma z parametrami kształtu 100 i skali 1
ks.test(x, "pgamma", shape=100, scale=1)
# hipotezy nie odrzucamy wiemy też że pochodzi

#wczytanie mieszkań
file <- read.csv2("../mieszkania.csv")

# zadanie 3 
# H0 -> metraż mieszkań ma rozkład normalny
shapiro.test(file$Metraz)
# odrzucamy hipotezę bo p-value jest bardzo małe

# zadanie 4
# a 
# regresja liniowa zależność ceny od metrażu
reglin <- lm(formula = file$Cena~file$Metraz)
summary(reglin)
coef(reglin)

# b
# rozrzut
plot(file$Metraz, file$Cena, ylab="Cena",xlab="Metraz")

# c 
# H0 -> hipoteza o rozkładzie normalnym reszt
prediction <- 72352.747 + file$Cena * 4755.602
rests <- file$Metraz - prediction
shapiro.test(rests)
# p < 0.05 więc odrzucamy hipotezę że reszty są z rozkładu normalnego

# d
# szacowanie ceny mieszkania 80m^2
72352.747 + 80 * 4755.602

#zadanie 5
file <- read.csv2("../bakteria.csv")
logM <- log10(file$masa)
# jak to wygląda
plot(file$czas, logM, ylab="log10(m)",xlab="t")

reglin <- lm(formula=logM~file$czas)
summary(reglin)

# funkcja zwracająca masę z naszej regresji
mass <- function(t) {
  calcMass <- 1.3472110 + t * 0.0870475
  return (10 ** calcMass)
}

# szacujemy masę bakterii na początku
mass(0)


