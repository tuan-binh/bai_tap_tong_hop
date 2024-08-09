import ra.entity.Category;
import ra.entity.Product;

import java.util.Scanner;

public class Main
{
	
	public static Category[] categories = new Category[100];
	public static int currentCate = 0;
	
	public static Product[] products = new Product[100];
	public static int currentPro = 0;
	
	
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		// show ra menu
		do
		{
			Main main = new Main();
			System.out.println("********************* SHOP *********************");
			System.out.println("1. Quản lý danh mục");
			System.out.println("2. Quản lý sản phẩm");
			System.out.println("3. Thoát");
			System.out.println("************************************************");
			System.out.println("Lựa chọn đê: ");
			int choice = Integer.parseInt(scanner.nextLine());
			switch (choice)
			{
				case 1:
				{
					main.menuCategory(scanner);
					break;
				}
				case 2:
				{
					main.menuProduct(scanner);
					break;
				}
				case 3:
				{
					System.exit(0);
					break;
				}
				default:
					System.err.println("Vui lòng nhập lại từ 1 -> 3");
			}
		}
		while (true);
	}
	
	public void menuCategory(Scanner scanner)
	{
		boolean isLoop = true;
		do
		{
			System.out.println("---------------------------CATEGORIES MENU---------------------------\n" +
					  "1. Nhập thông tin các danh mục\n" +
					  "2. Hiển thị thông tin các danh mục\n" +
					  "3. Cập nhật thông tin danh mục\n" +
					  "4. Xóa danh mục\n" +
					  "5. Cập nhật trạng thái danh mục\n" +
					  "6. Thoát");
			System.out.println("Lựa chọn đê: ");
			int choice = Integer.parseInt(scanner.nextLine());
			switch (choice)
			{
				case 1:
				{
					addNewCategories(scanner);
					break;
				}
				case 2:
				{
					showAllCategories();
					break;
				}
				case 3:
				{
					// các chức năng xem lại bài student
					break;
				}
				case 4:
				{
					// các chức năng xem lại bài student
					break;
				}
				case 5:
				{
					// các chức năng xem lại bài student
					// nhập vào id thay đổi trạng thái -> setStatus(! status cũ );
					break;
				}
				case 6:
				{
					isLoop = false;
					break;
				}
				default:
					System.err.println("Vui lòng nhập lại từ 1 -> 6");
			}
		}
		while (isLoop);
	}
	
	private void showAllCategories()
	{
		if (currentCate == 0)
		{
			System.err.println("Danh mục trống");
			return;
		}
		for (int i = 0; i < currentCate; i++)
		{
			categories[i].displayData();
		}
	}
	
	private void addNewCategories(Scanner sc)
	{
		System.out.println("Nhập số lượng muốn thêm: ");
		int n = Integer.parseInt(sc.nextLine()); // các em validation không được cho nó nhập số âm
		for (int i = 0; i < n; i++)
		{
			System.out.println("Nhập danh mục thứ " + (i + 1) + " : ");
			categories[currentCate] = new Category();
			categories[currentCate].inputData(sc, categories, currentCate);
			currentCate++;
		}
	}
	
	public void menuProduct(Scanner scanner)
	{
//		for (int i = 0; i < currentPro; i++)
//		{
//			products[i].displayData();
//		}
		boolean isLoop = true;
		do
		{
			System.out.println("---------------------------PRODUCT MANAGEMENT---------------------------\n" +
					  "\n" +
					  "1. Nhập thông tin các sản phẩm\n" +
					  "2. Hiển thị thông tin các sản phẩm\n" +
					  "3. Sắp xếp các sản phẩm theo giá\n" +
					  "4. Cập nhật thông tin sản phẩm theo mã sản phẩm\n" +
					  "5. Xóa sản phẩm theo mã sản phẩm\n" +
					  "6. Tìm kiếm các sản phẩm theo tên sản phẩm\n" +
					  "7. Tìm kiếm sản phẩm trong khoảng giá a – b (a,b nhập từ bàn phím)\n" +
					  "8. Thoát");
			System.out.println("Lựa chọn đê: ");
			int choice = Integer.parseInt(scanner.nextLine());
			switch (choice)
			{
				case 1:
				{
					addNewProduct(scanner);
					break;
				}
				case 2:
				{
					showAllProduct();
					break;
				}
				case 3:
				{
					// sắp xếp thì sử dụng thuật toán bubble sort để sắp xếp
					sortProductByPrice();
					break;
				}
				case 4:
				{
					break;
				}
				case 5:
				{
					break;
				}
				case 6:
				{
					// sử dụng for -> lấy name ra so sánh sử dụng contains( tìm kiếm ) xong gọi displayData() để hiển thị
					// nhập tên muốn tìm kiếm
					// products[i].getProductName().contains( tìm kiếm ) -> sử dung displayData()
					searchProductByName(scanner);
					break;
				}
				case 7:
				{
					searchByPrice(scanner);
					break;
				}
				case 8:
				{
					isLoop = false;
					break;
				}
				default:
					System.err.println("Vui lòng nhập lại từ 1 -> 6");
			}
		}
		while (isLoop);
	}
	
	private static void searchByPrice(Scanner scanner)
	{
		// nhập khoảng giá a = 100.000 ->  b = 200.000 => gọi displayData() -> để hiển thị nếu như giá của sản phẩm nằm trong từ a -> b
		System.out.println("Nhập khoảng giá bắt đầu: ");
		double from = Double.parseDouble(scanner.nextLine());
		System.out.println("Nhập khoảng giá kết thúc: ");
		double to = Double.parseDouble(scanner.nextLine());
		// nếu from > to ??? -> thực hiện
		for (int i = 0; i < currentPro; i++)
		{
			if (products[i].getPrice() >= from && products[i].getPrice() <= to)
			{
				products[i].displayData(categories, currentCate);
			}
		}
		// các em thực hiện nếu không có thì hiển thị ra thông báo không tìm thấy
	}
	
	private static void searchProductByName(Scanner scanner)
	{
		System.out.println("Nhập tên muốn tìm kiếm: ");
		String keyword = scanner.nextLine();
		for (int i = 0; i < currentPro; i++)
		{
			if (products[i].getProductName().contains(keyword))
			{
				products[i].displayData(categories, currentCate);
			}
		}
		// các em thực hiện nếu không có thì hiển thị ra thông báo không tìm thấy
	}
	
	private void sortProductByPrice()
	{
		for (int i = 0; i < currentPro; i++)
		{
			for (int j = i + 1; j < currentPro; j++)
			{
				if (products[i].getPrice() < products[j].getPrice())
				{
					Product temp = products[i];
					products[i] = products[j];
					products[j] = temp;
				}
			}
		}
		showAllProduct();
	}
	
	private void showAllProduct()
	{
		if (currentPro == 0)
		{
			System.err.println("Danh sách trống...");
			return;
		}
		for (int i = 0; i < currentPro; i++)
		{
			products[i].displayData(categories, currentCate);
		}
	}
	
	private void addNewProduct(Scanner scanner)
	{
		System.out.println("Nhập vào số lượng muốn thêm: ");
		int n = Integer.parseInt(scanner.nextLine()); // các em validate nếu nhập vào nhỏ hơn 0
		for (int i = 0; i < n; i++)
		{
			products[currentPro] = new Product();
			products[currentPro].inputData(scanner, products, currentPro, categories, currentCate);
			currentPro++;
		}
	}
}