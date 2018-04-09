# Luokkakaaviot

## Tehtävä 1
http://yuml.me/e5cf91cb.png

## Tehtävä 2
http://yuml.me/af04f740.png

## Tehtävä 3
title Machine creation and drive Sequence

Main->Machine: new Machine()
Machine->FuelTank: new FuelTank()
FuelTank-->Machine: FuelTank
Machine->FuelTank: fill(40)
Machine->Engine: new Engine(tank)
Engine-->Machine: Engine
Machine-->Main: Machine
Main->Machine: drive()
Machine->Engine: start()
Engine->FuelTank: consume(5)
Machine->Engine: isRunning()
Engine->FuelTank: contentsLeft()
FuelTank-->Engine: 35
Engine-->Machine: true
Machine->Engine: useEnergy()
Engine->FuelTank: consume(10)
