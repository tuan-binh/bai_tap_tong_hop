package ra.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Product
{
	private String productId;
	private String productName;
	private float price;
	private String description;
	private Date created;
	private int categoryId;
	private int productStatus;
	
	public Product()
	{
	}
	
	public Product(String productId, String productName, float price, String description, Date created, int categoryId, int productStatus)
	{
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.description = description;
		this.created = created;
		this.categoryId = categoryId;
		this.productStatus = productStatus;
	}
	
	public String getProductId()
	{
		return productId;
	}
	
	public void setProductId(String productId)
	{
		this.productId = productId;
	}
	
	public String getProductName()
	{
		return productName;
	}
	
	public void setProductName(String productName)
	{
		this.productName = productName;
	}
	
	public float getPrice()
	{
		return price;
	}
	
	public void setPrice(float price)
	{
		this.price = price;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public Date getCreated()
	{
		return created;
	}
	
	public void setCreated(Date created)
	{
		this.created = created;
	}
	
	public int getCategoryId()
	{
		return categoryId;
	}
	
	public void setCategoryId(int categoryId)
	{
		this.categoryId = categoryId;
	}
	
	public int getProductStatus()
	{
		return productStatus;
	}
	
	public void setProductStatus(int productStatus)
	{
		this.productStatus = productStatus;
	}
	
	public void inputData(Scanner sc, Product[] products, int currentPro, Category[] categories, int currentCate)
	{
		// nhập thông tin
		this.productId = inputProductId(sc, products, currentPro);
		this.productName = inputProductName(sc, products, currentPro);
		this.price = inputPrice(sc);
		System.out.println("Nhập mô tả sản phẩm: ");
		this.description = sc.nextLine();
		this.created = inputCreatedDate(sc);
		this.categoryId = inputCategoryId(sc, categories, currentCate);
		this.productStatus = inputProductStatus(sc);
	}
	
	public int inputProductStatus(Scanner sc)
	{
		do
		{
			System.out.println("1. Đang bán");
			System.out.println("2. Hết bán");
			System.out.println("3. Không bán");
			System.out.println("Nhập vào lựa chọn của bạn: ");
			int status = Integer.parseInt(sc.nextLine());
			if (status >= 1 && status <= 3)
			{
				return status - 1;
			}
			else
			{
				System.err.println("Vui lòng nhập lại từ 1 -> 3");
			}
		}
		while (true);
	}
	
	public int inputCategoryId(Scanner sc, Category[] categories, int currentCate)
	{
		// 1. show ra categories
		for (int i = 0; i < currentCate; i++)
		{
			System.out.printf("[ ID: %d | Name: %s ]\n", categories[i].getCatelogId(), categories[i].getCatelogName());
		}
		// 2. lựa chọn danh mục
		System.out.println("Lựa chọn danh mục muốn thêm: ");
		do
		{
			int idChoice = Integer.parseInt(sc.nextLine());
			boolean isExist = false;
			for (int i = 0; i < currentCate; i++)
			{
				if (categories[i].getCatelogId() == idChoice)
				{
					isExist = true;
					break;
				}
			}
			if (isExist)
			{
				return idChoice;
			}
			else
			{
				System.err.println("Không tồn tại danh mục đó");
			}
		}
		while (true);
	}
	
	public Date inputCreatedDate(Scanner sc)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Nhập vào ngày tạo dd/MM/yyyy: ");
		do
		{
			String date = sc.nextLine();
			try
			{
				return sdf.parse(date);
			}
			catch (ParseException e)
			{
				System.err.println("Nhập ngày không đúng định dạng");
			}
		}
		while (true);
	}
	
	public float inputPrice(Scanner sc)
	{
		System.out.println("Nhập vào giá sản phẩm: ");
		do
		{
			String price = sc.nextLine();
			if (price.trim().isEmpty())
			{
				System.err.println("Không được để trống");
			}
			else
			{
				float newPrice = Float.parseFloat(price);
				if (newPrice > 0)
				{
					return newPrice;
				}
				else
				{
					System.err.println("Vui lòng nhập giá tiền lớn hơn 0");
				}
			}
		}
		while (true);
	}
	
	public String inputProductName(Scanner sc, Product[] products, int currentPro)
	{
		System.out.println("Nhập vào tên sản phẩm: ");
		do
		{
			String name = sc.nextLine();
			if (name.trim().isEmpty())
			{
				System.err.println("Không được để trống");
			}
			else
			{
				if (name.length() >= 10 && name.length() <= 50)
				{
					boolean isExist = false;
					for (int i = 0; i < currentPro; i++)
					{
						if (products[i].getProductName().equals(name))
						{
							isExist = true;
							break;
						}
					}
					if (isExist)
					{
						System.err.println("Tên sản phẩm đã tồn tại");
					}
					else
					{
						return name;
					}
				}
				else
				{
					System.err.println("Tên sản phẩm phải từ 10 -> 50 ký tự");
				}
			}
		}
		while (true);
	}
	
	public String inputProductId(Scanner sc, Product[] products, int currentPro)
	{
		System.out.println("Nhập vào mã sản phẩm (Cxxx | Sxxx | Axxx): ");
		do
		{
			String id = sc.nextLine();
			if (id.matches("^[CSA]\\w{3}$")) // -> [CSA] -> C hoặc S hoặc A là 1 ký tự \\w bất kỳ ký tự nào {3} 3 ký tự
			{
				// w -> word , d -> digit , s -> space
				boolean isExist = false;
				for (int i = 0; i < currentPro; i++)
				{
					if (products[i].getProductId().equals(id))
					{
						isExist = true;
						break;
					}
				}
				if (isExist)
				{
					System.err.println("Mã sản phẩm đã bị trùng");
				}
				else
				{
					return id;
				}
			}
			else
			{
				System.err.println("Mã sản phẩm bắt đầu bằng ký tự C S A và 3 ký tự");
			}
		}
		while (true);
	}
	
	public void displayData(Category[] categories, int currentCate)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.printf(
				  "[ ID: %s | Name: %s | Price: %.2f | Desc: %s | Created: %s | Category: %s | Status: %s ]\n",
				  this.productId,
				  this.productName,
				  this.price,
				  this.description,
				  sdf.format(this.created),
				  getCatelogNameById(categories, currentCate),
				  this.productStatus == 0 ? "Đang bán" : this.productStatus == 1 ? "Hết hàng" : "Không bán"
		);
	}
	
	// hàm lấy tên category dựa vào id
	public String getCatelogNameById(Category[] categories, int currentCate)
	{
		// this là đại diện cho đối tượng chấm đến phương thức đó -> products[i].displayData() -> this = products[i] <=> { id, name, status, catalogId } riêng biệt
		for (int i = 0; i < currentCate; i++)
		{
			if (categories[i].getCatelogId() == this.categoryId)
			{
				return categories[i].getCatelogName();
			}
		}
		return null;
	}
}
