# INF124 HW3
Mike Duong 69881873

Bryan Linebaugh 49831189

UCI Informatics 124 HW1 Spring 2017

URL Address:

http://andromeda-42.ics.uci.edu:7777/HW3/index

Login:

- Use ics account username (For Putty Session)
- Run command "gsu inf124grp12"
- Login using personal ics password
- cd ..
- cd inf124grp12
- Done

Start Tomcat : $CATALINA_HOME/bin/startup.sh

Verify Tomcat Process : ps -ef | grep inf124grp12 | grep tomcat

Stop Tomcat : $CATALINA_HOME/bin/shutdown.sh

Summary:

The website is a high end clothing ecommerce website that specializes in shirts and pants.
There is a navigation bar above that allows the user to move from page to page. The
index/landing page gives a brief description of the website and a table of all the available 
products for sell with their image and price. When a user hovers over the item image, it 
will enlarge. The user can click on the image as well, which will take them to the product 
page.

The product page provides the details of each individual item from the index/landing page.
The user can hover over the small preview images to change the large image. Hovering over
the large image will also increase the size of it as well. Each detailed item also has a 
button that allow the user to order that particular item. When the "Add to cart" button is 
clicked, the item is added to the users cart.

User can go to their cart to checkout anytime and submit their order.

The company and about us page provide additional information about the company as well as
the developers of the website. 

The css folder contains the css file used to generate the css for the html documents.

The images folder contains all the images used for the website.
