# Magic-Hunter (0.1.0)
Magic Hunter is a project dedicated to finding orthogonal latin squares.  Currently this is a work in progress.  The goal of this project is to experiment with implementing Knuth's torodial data structure for the dancing links algorithm, as well as an implementation that uses parallism, and perhaps both.  Once completed, runtimes will be analyzed and compared for various sized latin squares.

## Problem Description ##

(From Wikipedia:) In combinatorics, a Graeco-Latin square or Euler square or orthogonal Latin squares of order n over two sets S and T, each consisting of n symbols, is an nxn arrangement of cells, each cell containing an ordered pair (s,t), where s is in S and t is in T, such that every row and every column contains each element of S and each element of T exactly once, and that no two cells contain the same ordered pair.

<br>
<p align="center">
<img src="https://raw.githubusercontent.com/Otays/Magic-Hunter/master/images/1.png" />
</p>

## Development Log ##
So far, the program can generate a latin square, and find an orthogonal mate if it exists.  

<br>
<p align="center">
<img src="https://raw.githubusercontent.com/Otays/Magic-Hunter/master/images/3.png" />
</p>


## To do ##
1. Right now the hunting algorithm is naive and sequential. Next step is adding parallelism and switching to Knuth's dancing links for better efficiency.

2. The interface is currently all in command line text.  Next step is to implement a swing interface.

3. The algorithm only provides the first orthogonal mate it can find.  To improve the software, it should instead allow more diverse queries such as finding all orthogonal mates.

4. Add javadoc
