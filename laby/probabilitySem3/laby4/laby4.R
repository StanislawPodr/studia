#zadanie 1
rand5000 <- runif(5000)
norm300 <- rnorm(3000, 100, 15)

hist(rand5000, breaks=100) #liczby w miarę stałe dla dużych interwałów
hist(norm300, breaks=100) #ładnie widać, że najwięcej wartości 
#wypada w okolicach 100, co jest 
#podaną przez nas wartością oczekiwaną

r5k <- density(rand5000)
r3 <- density(norm300)

plot(r5k) #oszacowany wykres gęstości
plot(r3)



#zadanie2
diceThrows <- ceiling(runif(600)*6)
avg <- mean(diceThrows) 
tab <-table(diceThrows) #tablica zliczająca ilość wyników
as.data.frame(tab)
var(tab)

sample(6, 600, replace=TRUE)


#zadanie3
rand <- runif(1000)
res <- array(0, dim=4)
for (i in rand) {
  if (i < 0.15) {
    res[1] <- res[1] + 1
  }
  else if (i < 0.40) {
    res[2] <- res[2] + 1
  }
  else if (i < 0.90) {
    res[3] <- res[3] + 1
  } 
  else {
    res[4] <- res[4] + 1
  }
}


#zadanie4
rbinom(100, 10, 0.3)
rgeom(50, 0.4) #ile prób do sukcesu potrzeba (50 razy powtórzone)



#zadanie5

#a
#d = 0.5x => dist = [0.25k^2] (dla k od 0 do x) 
# => dist = 0.25x^2 => x > 0 => x = 2*sqrt(dist)

distVec <- runif(200)
res <- 2*sqrt(distVec)


#b)
#losujemy z rozkładu ciagłego od 0 do 2, aby gęstość była zawsze 
# >= musimy pomnożyć do maksa którym w tym wypadku jest 1, więc k = 2
k <- 2

res <- c()
f <- function(x) {
  return(0.5*x)
}

g <- function(x) {
  return(0.5)
}

sampleNum <- 200

while (length(res) < sampleNum)
{
  x <- runif(1, min=0, max=2)
  u <- runif(1)
  if(u < f(x)/(k*g(x))) 
  {
    res <- c(res, x)
  }
}










