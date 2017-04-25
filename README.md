# Info124HW1
Mike Duong 69881873

Bryan Linebaugh 49831189

UCI Informatics 124 HW1 Spring 2017

URL Address:

http://andromeda-42.ics.uci.edu:5042

Login:

- Use ics account username (For Putty Session)
- Run command "gsu inf124grp12"
- Login using personal ics password
- cd ..
- cd inf124grp12
- Done

Start server : /usr/sbin/httpd -f /home/inf124grp12/apache/conf/httpd.conf

Stop Server : /usr/sbin/httpd --f /home/inf124grp12/apache/conf/httpd.conf --k stop

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
button that allow the user to order that particular item. When the "Order" button is 
clicked, a modal window will pop up and a billing form will be provided to the user to fill 
out.

The billing form allows the user to fill out their information for the sake of ordering the
item. All forms must be filled out correctly, otherwise the website will give a pop up 
warning sign. When the form is submitted, it will automatically launch the client's email
client.

The company and about us page provide additional information about the company as well as
the developers of the website. 

The css folder contains the css file used to generate the css for the html documents.

The images folder contains all the images used for the website.
