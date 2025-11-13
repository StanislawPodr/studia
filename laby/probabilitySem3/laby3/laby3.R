#zadanie 1
rzutMoneta5Reszek <- dbinom(5, 6, 0.5)
reszki3LubWiecej <- 1 - pbinom(2, 6, 0.5)
od2Do4Reszek <- pbinom(4, 6, 0.5) - pbinom(1, 6, 0.5)
plot(0:6, sapply(0:6, function(x) dbinom(x, 6, 0.5)), type = "h")

#zadanie2
dpois(5, 2)
1 - ppois(3, 2)
ppois(5, 2) - ppois(2, 2)
plot(0:30, sapply(0:30, function(x) dpois(x, 2)), type="h")

#zadanie3
punif(7, 4, 12)
punif(11, 4, 12) - punif(5, 4, 12)
1 - punif(10, 4, 12)
qunif(0.4, 4, 12)


#zadanie4
1 - pexp(0.5, 4)
pexp(1/3, 4)
pexp(80/60, 4) - pexp(2/3, 4)
qexp(0.8, 4)
plot((0:300)/100, sapply((0:300)/100, function(x) dexp(x, 4)))


#zadanie5
1 - pnorm(180, 170, 12)
pnorm(165, 170, 12)
pnorm(190, 170, 12) - pnorm(150, 170, 12)
qnorm(0.9, 170, 12)


#zadanie6
dbinom(27, 180, 1/6)
1 - pbinom(31, 180, 1/6)
pbinom(28, 180, 1/6)
pbinom(33, 180, 1/6) - pbinom(24, 180, 1/6)
