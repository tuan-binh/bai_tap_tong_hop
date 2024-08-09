package ra.entity;

import java.util.Scanner;

public class Category
{
	private int catelogId;
	private String catelogName;
	private String description;
	private Boolean catalogStatus;
	
	public Category()
	{
	}
	
	public Category(int catelogId, String catelogName, String description, Boolean catalogStatus)
	{
		this.catelogId = catelogId;
		this.catelogName = catelogName;
		this.description = description;
		this.catalogStatus = catalogStatus;
	}
	
	public int getCatelogId()
	{
		return catelogId;
	}
	
	public void setCatelogId(int catelogId)
	{
		this.catelogId = catelogId;
	}
	
	public String getCatelogName()
	{
		return catelogName;
	}
	
	public void setCatelogName(String catelogName)
	{
		this.catelogName = catelogName;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public Boolean getCatalogStatus()
	{
		return catalogStatus;
	}
	
	public void setCatalogStatus(Boolean catalogStatus)
	{
		this.catalogStatus = catalogStatus;
	}
	
	public void inputData(Scanner sc, Category[] categories, int currentCate)
	{
		this.catelogId = idAutoIncrement(categories, currentCate);
		this.catelogName = inputCatalogName(sc, categories, currentCate);
		System.out.println("Nhập mô tả danh mục: ");
		this.description = sc.nextLine();
		this.catalogStatus = inputCatalogStatus(sc);
	}
	
	public Boolean inputCatalogStatus(Scanner sc)
	{
		System.out.println("Nhập trạng thái: ");
		do
		{
			String status = sc.nextLine();
			if (status.trim().isEmpty())
			{
				System.err.println("Không được để trống");
			}
			else
			{
				if (status.equalsIgnoreCase("true") || status.equalsIgnoreCase("false"))
				{
					return Boolean.parseBoolean(status);
				}
				else
				{
					System.err.println("Vui lòng nhập lại true hoặc false");
				}
			}
		}
		while (true);
	}
	
	public String inputCatalogName(Scanner sc, Category[] categories, int currentCate)
	{
		System.out.println("Nhập tên danh mục: ");
		do
		{
			String catalogName = sc.nextLine();
			if (catalogName.trim().isEmpty())
			{
				System.err.println("Không được để trống");
			}
			else
			{
				if (catalogName.length() <= 50)
				{
					boolean isExist = false;
					for (int i = 0; i < currentCate; i++)
					{
						if (categories[i].getCatelogName().equals(catalogName))
						{
							isExist = true;
							break;
						}
					}
					if (isExist)
					{
						System.err.println("Tên danh mục đã bị trùng");
					}
					else
					{
						return catalogName;
					}
				}
				else
				{
					System.err.println("Chỉ được nhập tối đa 50 ký tự");
				}
			}
		}
		while (true);
	}
	
	public int idAutoIncrement(Category[] categories, int currentCate)
	{
		// 1. khởi tạo giá trị max ban đầu
		int maxId = 0;
		// 2. lặp qua đến phần tử hiện có là currentCate
		for (int i = 0; i < currentCate; i++)
		{
			// kiểm tra xem phần tử nào có id mà lớn hơn maxId thì gán giá trí vào biến maxId
			if (categories[i].getCatelogId() > maxId)
			{
				maxId = categories[i].getCatelogId();
			}
		}
		return maxId + 1;
	}
	
	public void displayData()
	{
		System.out.printf("[ ID: %d | Name: %s | Desc: %s | Status: %s ]\n", this.catelogId, this.catelogName, this.description, this.catalogStatus ? "Hoạt động" : "Không hoạt động");
	}
	
}
