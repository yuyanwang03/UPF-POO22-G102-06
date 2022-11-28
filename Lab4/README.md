# Lab 4 report

## 1. Introduction

The aim of this report is to describe how we have done lab 4, including all the problems we encountered and our solutions. First of all, our goal for this lab is to implement the design of seminar 4 emphasising the use of inheritance and code reuse. Unlike the other labs, we were not given any existing classes. We created 6 classes which are Vector, Matrix, AudioBuffer, Frame, BWFrame, ColorFrame, then the ImagePanel for the optional part and finally a Test class including the main method.

## 2. Implementation

In the explanation of the implementation of this Lab3, we will not go through all the existing classes/methods because some of them are just to simple and are not worth-mentioning. We will just mention some of the essential aspects of the code.

The first two classes we need to implement are Vector and Matrix. These two classes are for general use, which means we can reuse them in future labs if needed. The steps to create these two classes are pretty much similar to what we did in lab 1, and since there are only mathematical structures, we are not going deep into explaining this part. The only thing that needs to be kept in mind is the composition relation between these two: a matrix has arrays of vectors. Then, for the multiplyMatrix function in Vector class, we need to make sure their dimensions match. After the implementation, we include the test in the main method.

