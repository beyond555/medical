import os
import resume as r
def Hindex(path,AuthorMap,agencyMap):
	list = os.listdir(path)
	for i in range(0, len(list)):
		hindex=path+list[i]
		length=len(hindex)
		sub=hindex[0:length-4]
		print sub
		r.run(hindex,sub+'ans',AuthorMap,agencyMap)
