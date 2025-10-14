a <- c(1, 4, 6, 13, -10, 8)
b <- seq(1, 101, by = 2)
c <- rep(c(4, 7, 9), each = 3)
d <- c("czy", "to", "jest", "wektor", NA)
e <- c("czy", "to", "jest", "wektor", "NA")
f <- rep(c(4, 7, 9), times = 6)
length(a); typeof(a); min(a); max(a); sum(a)
length(b); typeof(b); min(b); max(b); sum(b)
length(c); typeof(c); min(c); max(c); sum(c)
length(d); typeof(d); min(d); max(d); sum(d)
length(e); typeof(e); min(e); max(e); sum(e)
length(f); typeof(f); min(f); max(f); sum(f)
sort(d)
sort(e)
a_f <- a + f
a_times_f <- a * f
a_c <- a + c
a_10 <- a + 10
a_times_15 <- 15 * a
b26 <- b[26]
f6_to_10 <- f[6:10]
higher_than_50_in_b <- b[b > 50]
