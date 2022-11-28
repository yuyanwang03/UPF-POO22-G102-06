
#ifndef _COMPARABLE_
#define _COMPARABLE_

template <class E>
class Comparable {
	virtual int compareTo(E * element) = 0;
};

#endif

