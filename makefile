all:
	javac -cp GraphicalFramework.jar:. *.java
	echo "#! /bin/bash \n java -cp GraphicalFramework.jar:. BattleshipGame" > run.sh 
	chmod +x run.sh
clean:
	rm *.class
	rm run.sh
