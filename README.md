# Graphical Particle Simulator
Owner: Wyatt Brown

This graphical Particle Simulator uses Processing and Java to display and simulate the interaction between particles and obstacles.
It also uses python's flask api and a csv file to store/retrive/save/update obstacles in the simulation. Due to the nature of Docker
images, this feature is not used in this Release.

To use this project:

- Run the docker image one of two ways:
  - Pull and run the Docker image from brow6946/particle-simulator
  - Use the provided Dockerfile and associated files to create and run a new docker image

- Once the Simulation has started:
  - Add particles using "+" and remove particles with "-".
  - Add Obstacles by left clicking in the simulation space, remove them by right clicking while hovering over the obstacle you would like to remove.
  - You can exit the simulation with "ESC."
