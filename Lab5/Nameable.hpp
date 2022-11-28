
#ifndef _NAMEABLE_
#define _NAMEABLE_

#include <iostream>

class Nameable {
	virtual std::string getName() = 0;
	virtual void setName(std::string s) = 0;
};

#endif

