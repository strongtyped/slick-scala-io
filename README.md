Implementing Active Record with Slick
==============

Step by step demo on how to implement the Active Record Pattern with Slick

This project is composed of an initial state and 8 steps. 

## Groll
The [groll](https://github.com/sbt/sbt-groll) sbt plugin is enabled for this project. You can navigate through the steps by simply running 'groll next' or 'groll prev'. NOTE: all uncommitted code is lost when you move from one step to the next using groll.

run 'groll initial' rewind to the 'Initial state'.


## Initial state
Defines 'Conference' model and Slick table mapping.

## Step 1
Demonstrates the available options in Slick to insert an Entity and assign the newly generated Id to the model class.

## Step 2
Defines IdTable with a well-known id:Column[Long]. 
This is the first building block to reduce the boilerplate.

## Step 3
Introduces the ActiveRecord trait and adds a 'save' method to the model using the pimp-my-library pattern.

## Step 4
Shows other advantages of having well-known id:Column
(findById, delete, filterById)

## Step 5
Makes id:Column type generic and shows what have to be done to achieve it (ie: usage of BaseColumnType in Context Bound).

## Step 6
Introduces Entity trait reducing the signature of IdTable and IdTableQuery via type projection of Entity#Id type.

## Step 7
Usage of Cake Pattern to allow driver swap.

## Step 8
Further boilerplate reduction by using Monocle Lens to eliminate 'withId' and 'extractId'.  
Minimalistic Entity trait (only defines a type alias for Id).
