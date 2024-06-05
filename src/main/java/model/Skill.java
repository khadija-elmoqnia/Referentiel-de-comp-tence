package model;


 public class Skill {
	 
	    private int id;
        private String name;
        private String description;
        private String domain;
        private int categorie_id;
        private Categorie categorie;

        // Constructeur
        public Skill(String name, String description, String domain, Categorie categorie) {
            this.name = name;
            this.description = description;
            this.domain = domain;
            this.categorie = categorie;
        }

    	public int getCategorie_id() {
			return categorie_id;
		}

		public void setCategorie_id(int categorie_id) {
			this.categorie_id = categorie_id;
		}

		public int getId() {
			return id;
		}

		public Skill() {
    	
    	}

    	public String getName() {
    		return name;
    	}

    	public void setName(String name) {
    		this.name = name;
    	}

    	public String getDescription() {
    		return description;
    	}

    	public void setDescription(String description) {
    		this.description = description;
    	}

    	public String getDomain() {
    		return domain;
    	}

    	public void setDomain(String domain) {
    		this.domain = domain;
    	}

    	public Categorie getCategorie() {
    		return categorie;
    	}

    	public void setCategorie(Categorie categorie) {
    		this.categorie = categorie;
    	}
    	
    	 

    	@Override
    	public String toString() {
    		return "Skill [name=" + name + ", description=" + description + ", domain=" + domain + ",Categorie=" + categorie + "]";
    	}

		public int getCategorie_Id() {
			return categorie_id;
			
		
		}
		public void setCategorie_Id(int a) {
			this.categorie_id=a;
			
		
		}

		public void setId(int int1) {
			this.id=int1;
			
		}

        
    }

